#define __STDC_LIMIT_MACROS=0
#define __STDC_CONSTANT_MACROS=0
#include <llvm/ADT/SmallVector.h>
#include <llvm/ADT/StringRef.h>
#include <llvm/IR/Value.h>
#include <llvm/Support/Casting.h>
#include <llvm/Support/raw_ostream.h>
#include <iterator>
#include <utility>

/*vevar.cc

 *
 *  Created on: Dec 14, 2014
 *      Author: priyanka
 */

#define DEBUG_TYPE "livevar"
#include "llvm/ADT/DenseMap.h"
#include "llvm/IR/Instruction.h"
#include <llvm/IR/Function.h>
#include <llvm/IR/InstIterator.h>
#include <llvm/IR/CFG.h>
#include <llvm/Pass.h>
#include <llvm/Support/Debug.h>

#include <set>

#include "llvm/IR/BasicBlock.h"
#include <llvm/ADT/ilist.h>

using namespace std;
using namespace llvm;
namespace {
DenseMap<const Instruction*, int> instrMap;

class inOut {
public:
	std::set<const Instruction*> inForBb;
	std::set<const Instruction*> outForBb;
};
class useDef {
public:
	std::set<const Instruction*> use;
	std::set<const Instruction*> def;

};
class Livevar: public FunctionPass {

	void insertInMap(Function &F) {
		static int id = 1;
		for (inst_iterator I = inst_begin(F), E = inst_end(F); I != E;
				++I, ++id) {
			errs() << *I << "\n";
			instrMap.insert(std::make_pair(&*I, id));
		}
	}

	void computeBlckUseDef(Function &func,
			DenseMap<const BasicBlock*, useDef> &bbmap) {
		for (Function::iterator blk = func.begin(), e = func.end(); blk != e;
				++blk) {
			errs() << "Basic block (name=" << blk->getName() << ") has "
					<< blk->size() << " instructions.\n";
			useDef usedef;
			for (BasicBlock::iterator i = blk->begin(), e = blk->end(); i != e;
					++i) {
				errs() << *i << "\n";
				unsigned nofOp = i->getNumOperands();
				for (unsigned oprd = 0l; oprd < nofOp; oprd++) {
					Value *v = i->getOperand(oprd);
					if (isa<Instruction>(v)) {
						Instruction *casInst = cast<Instruction>(v);
						if (!usedef.def.count(casInst))
							usedef.use.insert(casInst);
						usedef.def.insert(&*i);
					}
					bbmap.insert(make_pair(&*blk, usedef));
				}
			}
		}
	}

	void computeBlockInOut(Function &func,
			DenseMap<const BasicBlock*, useDef> &bbMap,
			DenseMap<const BasicBlock*, inOut> &bbIOMap) {
		SmallVector<BasicBlock*, 32> lst;
		lst.push_back(--func.end());

		while (!lst.empty()) {
			BasicBlock *bb = lst.pop_back_val();
			inOut io = bbIOMap.lookup(bb);
			bool isPred = !bbIOMap.count(bb);
			useDef ud = bbMap.lookup(bb);

			set<const Instruction*> unSucc;
			for (succ_iterator suc = succ_begin(bb), E = succ_end(bb); suc != E;
					++suc) {
				set<const Instruction*> sucset(bbIOMap.lookup(*suc).inForBb);
				unSucc.insert(sucset.begin(), sucset.end());
			}
			if (unSucc != io.outForBb) {
				isPred = true;
				io.outForBb = unSucc;
				io.inForBb.clear();

				set_difference(unSucc.begin(), unSucc.end(), ud.def.begin(),
						ud.def.end(), inserter(io.inForBb, io.inForBb.begin()));

			}
			if (isPred) {
				for (pred_iterator pit = pred_begin(bb), E = pred_end(bb);
						pit != E; ++pit) {
					lst.push_back(*pit);
				}
			}
		}
	}
	void computeLivness(Function &func,
			DenseMap<const BasicBlock*, inOut> &bbIOMap,
			DenseMap<const Instruction*, inOut> &bbIIOMap) {
		for (Function::iterator blk = func.begin(), e = func.end(); blk != e;
				++blk) {
			BasicBlock::iterator i = --blk->end();
			set<const Instruction*> liveOut(bbIOMap.lookup(blk).outForBb);
			set<const Instruction*> liveIn(liveOut);
			while (true) {
				liveIn.erase(i);
				unsigned nofOp = i->getNumOperands();
				for (unsigned oprd = 0l; oprd < nofOp; oprd++) {
					Value *v = i->getOperand(oprd);
					if (isa<Instruction>(v)) {
						liveIn.insert(cast<Instruction>(v));
					}
					inOut io;
					io.inForBb = liveIn;
					io.outForBb = liveOut;

					bbIIOMap.insert(make_pair(&*i, io));
					liveOut = liveIn;
					if (i == blk->begin()) {
						break;
						--i;
					}
				}
			}
		}
	}
public:
	static char ID;
	Livevar() :
			FunctionPass(ID) {
	}
	virtual bool runOnFunction(Function &F) {
		insertInMap(F);

		bool isAltered = false;
		DenseMap<const BasicBlock*, useDef> bbUDMap;
		computeBlckUseDef(F, bbUDMap);

		DenseMap<const BasicBlock*, inOut> bbIOMap;
		computeBlockInOut(F, bbUDMap, bbIOMap);

		DenseMap<const Instruction*, inOut> instIOMap;
		computeLivness(F, bbIOMap, instIOMap);


		return isAltered;
	}

};

}

