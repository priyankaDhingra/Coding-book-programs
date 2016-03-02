open Ll
open X86simplified
open LibUtil

let get_ind (u : uid) (ul : uid list) =
      let rec index_of (elt : uid) (l : uid list) : int = 
        begin match l with
        | [] -> failwith ("not found")
        | hd::tl ->
          if hd = elt
          then 0
          else 1 + index_of elt tl
        end in
				
        
      let idx = index_of u ul in
      let offset = Int32.of_int (4 * idx) in
				Ind { i_base = Some Esp;
              i_iscl = None;
              i_disp = Some (DImm offset) } 
              
let stack_offset_ebp (amt:int32) : operand =
  Ind{i_base = Some Ebp;
      i_iscl = None;
      i_disp = Some (DImm amt)} 
    
let get_oprnd (op: Ll.operand)(list_uid : uid list)  : X86simplified.operand =
  begin match op with
    | Ll.Const (x) -> X86simplified.Imm x
    | Ll.Local (u) -> get_ind u list_uid
  end
let emit_insn (inst:Ll.insn) (list_uid : uid list)=
	begin match inst with
	|Ll.Binop (id, b, op1, op2)-> let bin_opratn=begin match b with
 		| Ll.Add  -> Add (eax, ecx)
		| Ll.Sub  -> Sub (eax, ecx)
 		| Ll.Mul  -> Imul(ecx,Eax)
 		| Ll.Shl  -> Shl (eax, ecx)
 		| Ll.Lshr -> Shr (eax, ecx)
 		| Ll.Ashr -> Sar (eax, ecx)
 		| Ll.And  -> And (eax, ecx)
 		| Ll.Or   -> Or  (eax, ecx)
	 	| Ll.Xor  -> Xor (eax, ecx)
 	end in
 	let uid_val=get_ind id list_uid in
 	let oprnd1 = get_oprnd op1 list_uid in
 	let oprnd2 = get_oprnd op2 list_uid	 in
 	let cmn_inst = [Mov(oprnd2,ecx)]
 	@[Mov(oprnd1,eax)] in
 	(cmn_inst@[bin_opratn]@[Mov(uid_val,eax)])

	|Ll.Load (id,op1)-> let uid_val=get_ind id list_uid in 
	let oprnd1 = get_oprnd op1 list_uid in
	[Mov(oprnd1,ecx)]@[Mov(ecx,uid_val)]
	|Ll.Store (op1,op2)-> let oprnd1 = get_oprnd op1 list_uid in
 	let oprnd2 = get_oprnd op2 list_uid
	in 
	[Mov(oprnd1,ecx)]
 	@[Mov(oprnd2,eax)](*@
	[Mov(oprnd2,ecx)]@[Mov(ecx,oprnd1)]*)
	|Ll.Icmp (id, b, op1, op2)-> let cmp_opratn = begin match b with
		| Ll.Eq   -> [Setb(Eq,ecx)]
        | Ll.Ne   -> [Setb(NotEq,ecx)]
        | Ll.Slt   -> [Setb(Slt,ecx)]
        | Ll.Sle   -> [Setb(Sle,ecx)]
        | Ll.Sgt   -> [Setb(Sgt,ecx)]
        | Ll.Sge   -> [Setb(Sge,ecx)]
    	end in
    let oprnd1 = get_oprnd op1 list_uid in
 	let oprnd2 = get_oprnd op2 list_uid in    
    let cmn_inst = [Mov(oprnd1,ecx)]
 	@[Mov(oprnd2,eax)]
 	@[Cmp(ecx,eax)] in
    (cmn_inst@cmp_opratn@[And(ecx, Imm 1l)]@[Mov(oprnd1,ecx)])
    |Ll.Alloca u -> ([])
end
let emit_insns (bk:Ll.bblock) (list_uid :uid list)=
let rec emit_insn_fun (ins_list:Ll.insn list) (re:X86simplified.insn list)=
begin match ins_list with
	|[]->re
	|h::tl->emit_insn_fun tl (re@ (emit_insn h list_uid))
	end in 
	emit_insn_fun bk.insns []

let cntrl_fl (bb:Ll.bblock) (list_uid : uid list) =
begin match bb.terminator with
 | Ll.Ret o -> [Mov(eax, (get_oprnd o list_uid))]
    | Br lbl -> [Jmp (Lbl lbl)]
    | Cbr (op, lbl1, lbl2) -> let oprnd= get_oprnd op list_uid in
    [Cmp (oprnd, Imm 0l)]@[J(Eq, lbl1)]@[Jmp(Lbl lbl2)]
    @[J(NotEq, lbl1)]@[Jmp(Lbl lbl2)]
  end
let rec emit_uid_list (l : Ll.insn list) (accum : uid list) : uid list =
      begin match l with
      | [] -> accum
      | hd::tl -> 
        begin match hd with
        | Binop (u,_,_,_)  | Alloca u | Load (u,_) | Icmp (u,_,_,_) ->
          emit_uid_list tl (accum @ [u])
        | _ -> emit_uid_list tl (accum)
        end
      end 
      
let emit_blk ((blk_lst,list_uid) : (insn_block list*uid list)) (bk:Ll.bblock)  =
let uid_lst = emit_uid_list bk.insns list_uid in

let ctrf=cntrl_fl bk list_uid in 
 let blk=({X86simplified.global = false; 
 X86simplified.label = bk.label;
       X86simplified.insns= emit_insns bk uid_lst @ ctrf }) in
       (blk_lst@[blk],uid_lst)

let rec emit_blocks (blk:Ll.bblock list):insn_block list =
 let blk_list, u_list = List.fold_left emit_blk ([],[]) blk in
			(*let offset = Int32.of_int (-4 * (List.length u_list)) in*)
let epilogue=  ({X86simplified.global=false;
X86simplified.label=X86simplified.mk_lbl_named "_epilogue";
X86simplified.insns=[Pop(ecx)]@[Pop(edx)]@[Add(esp, Imm 100l)]@[Mov(ebp,esp)]
        @ [Pop(ebp)] @ [X86simplified.Ret]}) in
        blk_list @[epilogue]

let compile_prog (prog : Ll.prog) : Cunit.cunit =
    let block_name = (Platform.decorate_cdecl "program") in
	let program= Cunit.Code({X86simplified.global=true;	
	X86simplified.label=X86simplified.mk_lbl_named block_name;
	X86simplified.insns =[Push(ebp)] @ [Mov(ebp,esp)] @ [Sub(esp, Imm 100l)]
          @[Push(edx)]@[Push(ecx)]}) in 
    program:: List.map (fun blk -> Cunit.Code blk) (emit_blocks prog.ll_cfg)
