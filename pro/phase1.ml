open LibUtil

(* 
 * Parse and AST from a lexbuf 
 * - the filename is used to generate error messages
 *)
let parse (filename : string) (buf : Lexing.lexbuf) : Ast.prog =
  try
    Lexer.reset_lexbuf filename buf;
    Parser.toplevel Lexer.token buf
  with Parsing.Parse_error ->
    failwithf  "Parse error at %s." (Range.string_of_range (Lexer.lex_range buf))


(* 
 * Compile a source binop in to an LL instruction.
 *)
let compile_binop (b : Ast.binop) : Ll.uid -> Ll.operand -> Ll.operand -> Ll.insn  =
  let ib b id op1 op2 = (Ll.Binop (id, b, op1, op2)) in
  let ic c id op1 op2 = (Ll.Icmp (id, c, op1, op2)) in
  match b with
  | Ast.Plus  -> ib Ll.Add
  | Ast.Times -> ib Ll.Mul
  | Ast.Minus -> ib Ll.Sub
  | Ast.And   -> ib Ll.And
  | Ast.Or    -> ib Ll.Or
  | Ast.Shl   -> ib Ll.Shl
  | Ast.Shr   -> ib Ll.Lshr
  | Ast.Sar   -> ib Ll.Ashr

  | Ast.Eq    -> ic Ll.Eq
  | Ast.Neq   -> ic Ll.Ne
  | Ast.Lt    -> ic Ll.Slt
  | Ast.Lte   -> ic Ll.Sle
  | Ast.Gt    -> ic Ll.Sgt
  | Ast.Gte   -> ic Ll.Sge

let compile_unop (u:Ast.unop)(id:Ll.uid) (oprnd:Ll.operand):Ll.insn =
  match u with
  | Ast.Neg    -> (Ll.Binop (id, Ll.Mul, oprnd, Ll.Const(-1l)))
  | Ast.Lognot -> (Ll.Icmp (id, Ll.Eq, oprnd, Ll.Const(0l)))
  | Ast.Not    -> (Ll.Binop (id, Ll.Xor, oprnd, Ll.Const(-1l)))

let create_block (l:Ll.lbl) (i:Ll.insn list) (j:Ll.terminator):Ll.bblock = {
    Ll.label= l;
    Ll.insns= i;
    Ll.terminator= j
  }

let rec compile_exp (e : Ast.exp) (env:(string * Ll.uid) list) =
match e with
| Ast.Cint i-> let uid_alloca=Ll.gen_sym() in
(uid_alloca,[Ll.Binop(uid_alloca, Ll.Add,Ll.Const 0l , Ll.Const i)])
| Ast.Id (x)-> let uid_alloca=Ll.gen_sym() in
	let oprnd =Ll.Local (List.assoc x env )in
	(uid_alloca,[Ll.Load (uid_alloca,oprnd)])
| Ast.Unop (op, l)->let (oprnd,inst)=compile_exp e env in
	let uid_alloca=Ll.gen_sym() in
	(uid_alloca,inst@[compile_unop op uid_alloca (Ll.Local oprnd)])
| Ast.Binop (op, l,r)-> let (oprndl,instl) = compile_exp l env in
   	let (oprndr,instr) = compile_exp r env in
   	let uid_alloca=Ll.gen_sym() in
    let op_ins = compile_binop op uid_alloca (Ll.Local oprndl) (Ll.Local oprndr) in
    (uid_alloca, instl@instr@[op_ins])

let compile_vdecl ((insns,cntxt):  Ll.insn list * (string * Ll.uid) list)
 (var_dec : Ast.var_decl)  =
	let var_uid =Ll.mk_uid var_dec.Ast.v_id in
	let var_ctxt=((var_dec.Ast.v_id,var_uid) ::cntxt) in
	let (oprnd,inst)=compile_exp var_dec.Ast.v_init cntxt in
	let vardecl_insn = Ll.Alloca(var_uid)::Ll.Store(Ll.Local oprnd, Ll.Local var_uid)::[] in
	let insn_cat = insns@inst@ vardecl_insn in
	(insn_cat, var_ctxt)

let rec compile_stmt ((cntxt ,l): (string * Ll.uid) list* Ll.lbl) (s : Ast.stmt): 
(Ll.lbl* Ll.bblock list)=
 
let compile_opt (sopt:'a option)=
	match sopt with
	|None-> let ifop_lbl = X86simplified.mk_lbl() in
	(ifop_lbl,[create_block ifop_lbl [] (Ll.Br l)])
	|Some s-> compile_stmt (cntxt,l) s
in
begin match s with
   	| Ast.Assign (left, e) -> let var_uid = 
   		begin match left with
		|Ast.Var v-> List.assoc v cntxt
		end in
		let (oprnd,inst)=compile_exp e cntxt in 
		let assign_insn = inst @ [Ll.Store ((Ll.Local oprnd),(Ll.Local var_uid))] in
		let assign_lbl=X86simplified.mk_lbl() in
		(assign_lbl, [create_block assign_lbl assign_insn (Ll.Br l)])
    | Ast.If(e, s, sopt) ->let (oprnd,inst)=compile_exp e cntxt in
    	let (thn_lbl,thn_blk_list)=compile_stmt (cntxt,l) s in
    	let (else_lbl,else_blk_list)=compile_opt sopt in
    	let if_lbl=X86simplified.mk_lbl() in
    	(if_lbl, [create_block if_lbl inst (
    	Ll.Cbr (Ll.Local oprnd, thn_lbl, else_lbl))]@ 
    	thn_blk_list@
    	else_blk_list)
    | Ast.While(e, s) -> let (oprnd,inst)=compile_exp e cntxt in
    	let wh_lbl =  X86simplified.mk_lbl() in
    	let (thn_lbl,thn_blk_list)=compile_stmt (cntxt, wh_lbl) s in
    	(wh_lbl, [create_block wh_lbl inst (
    	Ll.Cbr (Ll.Local oprnd, thn_lbl, l))]@ thn_blk_list)
    | Ast.For(vdl, eopt, sopt, s) -> let (var_insns, var_ctxt)=
   		List.fold_left compile_vdecl ([],cntxt) vdl in
     	let for_lbl=X86simplified.mk_lbl() in
     	let init_lbl=X86simplified.mk_lbl() in   	
     	let ( thn_blk_list,thn_lbl) = begin match sopt with
			|None-> let lb,blk = compile_stmt (var_ctxt,init_lbl) s in blk,lb
			|Some s1-> List.fold_right (fun stmnt (blk_list, blk_lbl)->
			let st_lbl,st_blk= compile_stmt (var_ctxt,blk_lbl) stmnt in
			(st_blk@blk_list,st_lbl))
			 (s::s1::[]) ([], init_lbl) 
			end in
		let init_blk =  begin match eopt with
			|None-> create_block init_lbl [] (Ll.Br thn_lbl)
			|Some s1-> let (oprnd,inst)=compile_exp s1 cntxt in
			 create_block init_lbl inst (Ll.Cbr (Ll.Local oprnd, thn_lbl, l))
			end in
		(for_lbl,[create_block for_lbl var_insns (Ll.Br init_lbl)]@
		[init_blk]@
		thn_blk_list)	   
    | Ast.Block b -> let blk_lbl, blk_list =compile_blk b cntxt l
		in (blk_lbl,blk_list)
    end
    and compile_stmts (s : Ast.stmt list) (c : (string * Ll.uid) list) (next : Ll.lbl) 
    : Ll.lbl * Ll.bblock list =
	let block_list, next_l = 
  		List.fold_right (
  			fun st (bblist, next_lbl) ->
  				let s_lbl, s_blk = compile_stmt (c, next_lbl) st in
  				(s_blk @ bblist, s_lbl)
  				) s ([], next) in
		(next_l, block_list)
    and
compile_blk (blck : Ast.block) (cntxt :(string * Ll.uid) list) (l : Ll.lbl): 
(Ll.lbl* Ll.bblock list) =
	
	let (var_decl,stmnts)=blck in
	let ( inst,local_ctxt) =  List.fold_left compile_vdecl ( [],cntxt) var_decl in
	let (lab, bblock_list)= compile_stmts stmnts cntxt l
	 in
	let block_lbl = Ll.mk_lbl() in
	 (block_lbl,[create_block block_lbl inst (Ll.Br lab)]@ bblock_list)
	 
	
let compile_blok (blck : Ast.block) (cntxt :(string * Ll.uid) list) (l : Ll.lbl): 
(Ll.lbl* Ll.bblock list*(string * Ll.uid) list) =
	
	let (var_decl,stmnts)=blck in
	let ( inst,local_ctxt) =  List.fold_left compile_vdecl ( [],cntxt) var_decl in
	let (lab, bblock_list)= compile_stmts stmnts cntxt l
	in
	let block_lbl = X86simplified.mk_lbl() in
	 (block_lbl,([create_block block_lbl inst (Ll.Br lab)]@bblock_list),local_ctxt)
	
	
let compile_prog ((block, ret):Ast.prog) : Ll.prog =
let lb=X86simplified.mk_lbl() in
let main_lbl, list_blk , ctxt=compile_blok block [] lb in
let res_uid, res_insn_list =compile_exp ret ctxt in
{Ll.ll_cfg=list_blk@[create_block lb res_insn_list (Ll.Ret (Ll.Local res_uid))] ; 
Ll.ll_entry= main_lbl}