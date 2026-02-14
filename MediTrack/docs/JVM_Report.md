# JVM Architecture Report

## Class Loader
- Bootstrap
- Extension
- Application

## Runtime Data Areas
- Heap (objects)
- Stack (method frames)
- Method Area (class metadata)
- PC Register
- Native Method Stack

## Execution Engine
- Interpreter
- JIT Compiler

## JIT vs Interpreter
- Interpreter: line-by-line execution
- JIT: compiles hot code paths to native code

## Write Once, Run Anywhere
Java bytecode runs on any OS with a JVM.
