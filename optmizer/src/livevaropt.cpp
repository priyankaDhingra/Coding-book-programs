/*
 * livevaropt.cpp

 *
 *  Created on: Dec 13, 2014
 *      Author: priyanka
 */

#define __STDC_LIMIT_MACROS=0
#define __STDC_CONSTANT_MACROS=0
#include "llvm/IR/BasicBlock.h"
#include "llvm/IR/Constants.h"
#include "llvm/IR/LLVMContext.h"
#include "llvm/Pass.h"
#include "llvm/IR/User.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/Module.h"
#include "llvm/IR/DerivedTypes.h"
#include "llvm/IR/Instructions.h"
#include "llvm/IR/InstrTypes.h"
#include "llvm/Support/FormattedStream.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/ADT/Twine.h"

#include <ostream>
#include <fstream>
#include <sstream>
#include <iostream>
#include <string>
#include <list>
#include <vector>
#include <map>

using namespace std;
using namespace llvm;
namespace {
class Livevaropt:public ModulePass
{
	struct cfg {
		list <BasicBlock*> nodes;

	};
};
}



