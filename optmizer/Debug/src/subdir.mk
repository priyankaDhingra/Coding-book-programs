################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Live.cpp \
../src/ReachingDef.cpp \
../src/liveVars.cpp \
../src/livevar.cpp \
../src/livevaropt.cpp \
../src/optmizer.cpp 

OBJS += \
./src/Live.o \
./src/ReachingDef.o \
./src/liveVars.o \
./src/livevar.o \
./src/livevaropt.o \
./src/optmizer.o 

CPP_DEPS += \
./src/Live.d \
./src/ReachingDef.d \
./src/liveVars.d \
./src/livevar.d \
./src/livevaropt.d \
./src/optmizer.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	c++ -O0 -g3 -Wall -c -fmessage-length=0 -std=c++11 libtool -dynamic -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


