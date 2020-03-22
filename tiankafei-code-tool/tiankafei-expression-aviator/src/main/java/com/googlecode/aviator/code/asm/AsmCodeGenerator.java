/**
 * Copyright (C) 2010 dennis zhuang (killme2008@gmail.com)
 * <p>
 * This library is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with this program;
 * if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 **/
package com.googlecode.aviator.code.asm;

import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.BaseClassExpression;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.asm.BaseMethodVisitor;
import com.googlecode.aviator.asm.ClassWriter;
import com.googlecode.aviator.asm.Label;
import com.googlecode.aviator.asm.Opcodes;
import com.googlecode.aviator.code.CodeGenerator;
import com.googlecode.aviator.code.LambdaGenerator;
import com.googlecode.aviator.exception.CompileExpressionErrorException;
import com.googlecode.aviator.exception.ExpressionRuntimeException;
import com.googlecode.aviator.lexer.token.NumberToken;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.lexer.token.Token;
import com.googlecode.aviator.lexer.token.Token.TokenType;
import com.googlecode.aviator.lexer.token.Variable;
import com.googlecode.aviator.parser.AviatorClassLoader;
import com.googlecode.aviator.parser.Parser;
import com.googlecode.aviator.runtime.FunctionArgument;
import com.googlecode.aviator.runtime.LambdaFunctionBootstrap;
import com.googlecode.aviator.runtime.op.OperationRuntime;
import com.googlecode.aviator.utils.Env;
import com.googlecode.aviator.utils.TypeUtils;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

import static com.googlecode.aviator.asm.Opcodes.ACC_FINAL;
import static com.googlecode.aviator.asm.Opcodes.ACC_PRIVATE;
import static com.googlecode.aviator.asm.Opcodes.ACC_PUBLIC;
import static com.googlecode.aviator.asm.Opcodes.ACC_SUPER;
import static com.googlecode.aviator.asm.Opcodes.ACONST_NULL;
import static com.googlecode.aviator.asm.Opcodes.ALOAD;
import static com.googlecode.aviator.asm.Opcodes.ARETURN;
import static com.googlecode.aviator.asm.Opcodes.ASTORE;
import static com.googlecode.aviator.asm.Opcodes.CHECKCAST;
import static com.googlecode.aviator.asm.Opcodes.DUP;
import static com.googlecode.aviator.asm.Opcodes.GETFIELD;
import static com.googlecode.aviator.asm.Opcodes.GETSTATIC;
import static com.googlecode.aviator.asm.Opcodes.GOTO;
import static com.googlecode.aviator.asm.Opcodes.IFEQ;
import static com.googlecode.aviator.asm.Opcodes.IFGE;
import static com.googlecode.aviator.asm.Opcodes.IFGT;
import static com.googlecode.aviator.asm.Opcodes.IFLE;
import static com.googlecode.aviator.asm.Opcodes.IFLT;
import static com.googlecode.aviator.asm.Opcodes.IFNE;
import static com.googlecode.aviator.asm.Opcodes.INVOKEINTERFACE;
import static com.googlecode.aviator.asm.Opcodes.INVOKESPECIAL;
import static com.googlecode.aviator.asm.Opcodes.INVOKESTATIC;
import static com.googlecode.aviator.asm.Opcodes.INVOKEVIRTUAL;
import static com.googlecode.aviator.asm.Opcodes.NEW;
import static com.googlecode.aviator.asm.Opcodes.POP;
import static com.googlecode.aviator.asm.Opcodes.PUTFIELD;
import static com.googlecode.aviator.asm.Opcodes.RETURN;
import static com.googlecode.aviator.asm.Opcodes.SWAP;


/**
 * Code generator using asm
 *
 * @author dennis
 */
public class AsmCodeGenerator implements CodeGenerator {

    public static final String FUNC_ARGS_INNER_VAR = "__fas__";
    private static final String FIELD_PREFIX = "f";
    private final AviatorEvaluatorInstance instance;
    /**
     * Compile environment only has the *instance*.
     */
    private final Env compileEnv;
    private final ClassWriter classWriter;
    private BaseMethodVisitor mv;
    private final String className;
    private final AviatorClassLoader classLoader;
    private LambdaGenerator lambdaGenerator;
    private Parser parser;

    private static final AtomicLong CLASS_COUNTER = new AtomicLong();

    /**
     * Operands count to check stack frames
     */
    private int operandsCount = 0;

    private int maxStacks = 0;
    private int maxLocals = 2;

    private int fieldCounter = 0;

    private Map<String, String> innerVars =
            Collections.emptyMap();
    private Map<String, String> innerMethodMap =
            Collections.emptyMap();

    private Map<String, Integer> varTokens = Collections.emptyMap();
    private Map<String, Integer> methodTokens = Collections.emptyMap();

    private final Map<Label, Map<String, Integer>> labelNameIndexMap =
            new IdentityHashMap<Label, Map<String, Integer>>();
    /**
     * function params info.
     */
    private Map<Integer, List<FunctionArgument>> funcsArgs;

    private int funcInvocationId = 0;

    /**
     * Compiled lambda functions.
     */
    private Map<String, LambdaFunctionBootstrap> lambdaBootstraps;

    private static final Label START_LABEL = new Label();

    private Label currentLabel = START_LABEL;

    /**
     * parent code generator when compiling lambda.
     */
    private CodeGenerator parentCodeGenerator;

    @Override
    public void setParser(final Parser parser) {
        this.parser = parser;
    }


    private void setMaxStacks(final int newMaxStacks) {
        if (newMaxStacks > this.maxStacks) {
            this.maxStacks = newMaxStacks;
        }
    }

    private Map<Integer/* internal function id */, List<FunctionArgument>> getFuncsArgs() {
        if (this.funcsArgs == null) {
            this.funcsArgs = new HashMap<>(100);
        }
        return this.funcsArgs;
    }

    private int getNextFuncInvocationId() {
        return this.funcInvocationId++;
    }

    public AsmCodeGenerator(final AviatorEvaluatorInstance instance,
                            final AviatorClassLoader classLoader, final OutputStream traceOut, final boolean trace) {
        this.classLoader = classLoader;
        this.instance = instance;
        this.compileEnv = new Env();
        this.compileEnv.setInstance(this.instance);
        // Generate inner class name
        this.className = "Script_" + System.currentTimeMillis() + "_" + CLASS_COUNTER.getAndIncrement();
        // Auto compute frames
        this.classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // if (trace) {
        // this.traceClassVisitor = new TraceClassVisitor(this.clazzWriter, new PrintWriter(traceOut));
        // this.classWriter = new CheckClassAdapter(this.traceClassVisitor);
        // } else {
        // this.classWriter = new CheckClassAdapter(this.clazzWriter);
        // }
        visitClass();
    }


    public AviatorClassLoader getClassLoader() {
        return this.classLoader;
    }


    LambdaGenerator getLambdaGenerator() {
        return this.lambdaGenerator;
    }


    public void start() {
        makeConstructor();
        startVisitMethodCode();
    }


    private void startVisitMethodCode() {
        this.mv = this.classWriter.visitMethod(ACC_PUBLIC + +ACC_FINAL, "execute0",
                "(Lcom/googlecode/aviator/utils/Env;)Ljava/lang/Object;",
                "(Lcom/googlecode/aviator/utils/Env;)Ljava/lang/Object;", null);
        this.mv.visitCode();
    }


    private void endVisitMethodCode() {
        if (this.operandsCount > 0) {
            loadEnv();
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                    "getValue", "(Ljava/util/Map;)Ljava/lang/Object;");
            this.mv.visitInsn(ARETURN);
            this.popOperand();
            this.popOperand();
        } else {
            this.mv.visitInsn(ACONST_NULL);
            this.mv.visitInsn(ARETURN);
            this.pushOperand();
            this.popOperand();
        }
        if (this.operandsCount > 0) {
            throw new CompileExpressionErrorException(
                    "operand stack is not empty,count=" + this.operandsCount);
        }
        this.mv.visitMaxs(this.maxStacks, this.maxLocals);
        this.mv.visitEnd();

    }


    private void endVisitClass() {
        this.classWriter.visitEnd();
    }


    /**
     * Make a default constructor
     */
    private void makeConstructor() {
        {
            this.mv = this.classWriter.visitMethod(ACC_PUBLIC, "<init>",
                    "(Lcom/googlecode/aviator/AviatorEvaluatorInstance;Ljava/util/List;)V", null, null);
            this.mv.visitCode();
            this.mv.visitVarInsn(ALOAD, 0);
            this.mv.visitVarInsn(ALOAD, 1);
            this.mv.visitVarInsn(ALOAD, 2);
            this.mv.visitMethodInsn(INVOKESPECIAL, "com/googlecode/aviator/BaseClassExpression", "<init>",
                    "(Lcom/googlecode/aviator/AviatorEvaluatorInstance;Ljava/util/List;)V");
            if (!this.innerVars.isEmpty()) {
                for (Map.Entry<String, String> entry : this.innerVars.entrySet()) {
                    String outterName = entry.getKey();
                    String innerName = entry.getValue();
                    this.mv.visitVarInsn(ALOAD, 0);
                    this.mv.visitTypeInsn(NEW, "com/googlecode/aviator/runtime/type/AviatorJavaType");
                    this.mv.visitInsn(DUP);
                    this.mv.visitLdcInsn(outterName);
                    this.mv.visitMethodInsn(INVOKESPECIAL,
                            "com/googlecode/aviator/runtime/type/AviatorJavaType", "<init>",
                            "(Ljava/lang/String;)V");
                    this.mv.visitFieldInsn(PUTFIELD, this.className, innerName,
                            "Lcom/googlecode/aviator/runtime/type/AviatorJavaType;");
                }
            }
            if (!this.innerMethodMap.isEmpty()) {
                for (Map.Entry<String, String> entry : this.innerMethodMap.entrySet()) {
                    String outterName = entry.getKey();
                    String innerName = entry.getValue();
                    this.mv.visitVarInsn(ALOAD, 0);
                    this.mv.visitVarInsn(ALOAD, 1);
                    this.mv.visitLdcInsn(outterName);
                    this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/AviatorEvaluatorInstance",
                            "getFunction",
                            "(Ljava/lang/String;)Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
                    this.mv.visitFieldInsn(PUTFIELD, this.className, innerName,
                            "Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
                }
            }

            this.mv.visitInsn(RETURN);
            this.mv.visitMaxs(4, 1);
            this.mv.visitEnd();
        }
    }


    private void visitClass() {
        this.classWriter.visit(this.instance.getBytecodeVersion(), ACC_PUBLIC + ACC_SUPER,
                this.className, null, "com/googlecode/aviator/BaseClassExpression", null);
    }


    /**
     * Make a label
     *
     * @return
     */
    private Label makeLabel() {
        return new Label();
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onAdd(com.googlecode.aviator .lexer.token.Token)
     */
    @Override
    public void onAdd(final Token<?> lookhead) {
        visitBinOperator(OperatorType.ADD, "add");
    }

    private void loadOpType(final OperatorType opType) {
        this.pushOperand();
        this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/lexer/token/OperatorType",
                opType.name(), "Lcom/googlecode/aviator/lexer/token/OperatorType;");
    }


    /**
     * Pop a operand from stack
     */
    private void popOperand() {
        this.operandsCount--;
    }


    /**
     * Pop a operand from stack
     */
    private void popOperand(final int n) {
        this.operandsCount -= n;
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onSub(com.googlecode.aviator .lexer.token.Token)
     */
    @Override
    public void onSub(final Token<?> lookhead) {
        visitBinOperator(OperatorType.SUB, "sub");
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onMult(com.googlecode.aviator
     * .lexer.token.Token)
     */
    @Override
    public void onMult(final Token<?> lookhead) {
        visitBinOperator(OperatorType.MULT, "mult");
    }


    @Override
    public void onAssignment(final Token<?> lookhead) {
        loadEnv();

        this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/AviatorJavaType",
                "setValue",
                "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        this.popOperand(3);
        this.pushOperand();
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onDiv(com.googlecode.aviator .lexer.token.Token)
     */
    @Override
    public void onDiv(final Token<?> lookhead) {
        visitBinOperator(OperatorType.DIV, "div");
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onMod(com.googlecode.aviator .lexer.token.Token)
     */
    @Override
    public void onMod(final Token<?> lookhead) {
        visitBinOperator(OperatorType.MOD, "mod");
    }


    /**
     * Do logic operation "&&" left operand
     */
    @Override
    public void onAndLeft(final Token<?> lookhead) {
        loadEnv();
        visitLeftBranch(IFEQ, OperatorType.AND);
    }


    private void visitBoolean() {
        this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                "booleanValue", "(Ljava/util/Map;)Z");
    }


    private void pushLabel0(final Label l0) {
        this.l0stack.push(l0);
    }


    /**
     * Do logic operation "&&" right operand
     */
    @Override
    public void onAndRight(final Token<?> lookhead) {
        visitRightBranch(IFEQ, OperatorType.AND);
        // boolean object and environment
        this.popOperand(2);
        this.pushOperand();
    }


    private void visitRightBranch(final int ints, final OperatorType opType) {
        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, opType)) {
            loadEnv();
            String first = "TRUE";
            String second = "FALSE";
            if (opType == OperatorType.OR) {
                first = "FALSE";
                second = "TRUE";
            }

            visitBoolean();
            this.mv.visitJumpInsn(ints, peekLabel0());
            // Result is true
            this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean", first,
                    "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
            Label l1 = makeLabel();
            this.mv.visitJumpInsn(GOTO, l1);
            visitLabel(popLabel0());
            // Result is false
            this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean",
                    second, "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
            visitLabel(l1);
        } else {
            loadOpType(opType);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/op/OperationRuntime",
                    "eval",
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Lcom/googlecode/aviator/lexer/token/OperatorType;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            this.popOperand();
        }
    }

    /**
     * Label stack for ternary operator
     */
    private final Stack<Label> l0stack = new Stack<Label>();
    private final Stack<Label> l1stack = new Stack<Label>();


    @Override
    public void onTernaryBoolean(final Token<?> lookhead) {
        loadEnv();
        visitBoolean();
        Label l0 = makeLabel();
        Label l1 = makeLabel();
        pushLabel0(l0);
        pushLabel1(l1);
        this.mv.visitJumpInsn(IFEQ, l0);
        this.popOperand();
        this.popOperand();
        // add two booleans
        this.pushOperand(2);

        // pop the last result
        this.popOperand();
    }


    private void pushLabel1(final Label l1) {
        this.l1stack.push(l1);
    }


    @Override
    public void onTernaryLeft(final Token<?> lookhead) {
        this.mv.visitJumpInsn(GOTO, peekLabel1());
        visitLabel(popLabel0());
        this.popOperand(); // pop one boolean
    }


    private Label peekLabel1() {
        return this.l1stack.peek();
    }


    @Override
    public void onTernaryRight(final Token<?> lookhead) {
        visitLabel(popLabel1());
        this.popOperand(); // pop one boolean
    }


    @Override
    public void onTernaryEnd(final Token<?> lookhead) {
        while (--this.operandsCount > 0) {
            this.mv.visitInsn(POP);
        }
    }

    private Label popLabel1() {
        return this.l1stack.pop();
    }


    /**
     * Do logic operation "||" right operand
     */
    @Override
    public void onJoinRight(final Token<?> lookhead) {
        visitRightBranch(IFNE, OperatorType.OR);
        this.popOperand(2);
        this.pushOperand();

    }


    private void visitLabel(final Label label) {
        this.mv.visitLabel(label);
        this.currentLabel = label;
    }


    private Label peekLabel0() {
        return this.l0stack.peek();
    }


    private Label popLabel0() {
        return this.l0stack.pop();
    }


    /**
     * Do logic operation "||" left operand
     */
    @Override
    public void onJoinLeft(final Token<?> lookhead) {
        loadEnv();
        visitLeftBranch(IFNE, OperatorType.OR);
    }


    private void visitLeftBranch(final int ints, final OperatorType opType) {
        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, opType)) {
            visitBoolean();
            Label l0 = makeLabel();
            pushLabel0(l0);
            this.mv.visitJumpInsn(ints, l0);
            this.popOperand();
        }
        this.popOperand();
    }


    @Override
    public void onEq(final Token<?> lookhead) {
        doCompareAndJump(IFNE, OperatorType.EQ);
    }


    @Override
    public void onMatch(final Token<?> lookhead) {
        visitBinOperator(OperatorType.MATCH, "match");
        this.popOperand();
        this.pushOperand();
    }


    @Override
    public void onNeq(final Token<?> lookhead) {
        doCompareAndJump(IFEQ, OperatorType.NEQ);
    }


    private void doCompareAndJump(final int ints, final OperatorType opType) {
        loadEnv();
        visitCompare(ints, opType);
        this.popOperand();
        this.popOperand();
    }


    private void visitCompare(final int ints, final OperatorType opType) {
        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, opType)) {
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                    "compare", "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;)I");
            Label l0 = makeLabel();
            Label l1 = makeLabel();
            this.mv.visitJumpInsn(ints, l0);
            this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean",
                    "TRUE", "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
            this.mv.visitJumpInsn(GOTO, l1);
            visitLabel(l0);
            this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean",
                    "FALSE", "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
            visitLabel(l1);
        } else {
            loadOpType(opType);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/op/OperationRuntime",
                    "eval",
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;Lcom/googlecode/aviator/lexer/token/OperatorType;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            this.popOperand();
        }

    }


    @Override
    public void onGe(final Token<?> lookhead) {
        doCompareAndJump(IFLT, OperatorType.GE);
    }


    @Override
    public void onGt(final Token<?> lookhead) {
        doCompareAndJump(IFLE, OperatorType.GT);
    }


    @Override
    public void onLe(final Token<?> lookhead) {
        doCompareAndJump(IFGT, OperatorType.LE);

    }


    @Override
    public void onLt(final Token<?> lookhead) {
        doCompareAndJump(IFGE, OperatorType.LT);
    }

    public void pushOperand(final int delta) {
        this.operandsCount += delta;
        setMaxStacks(this.operandsCount);
    }


    /**
     * Logic operation '!'
     */
    @Override
    public void onNot(final Token<?> lookhead) {
        visitUnaryOperator(OperatorType.NOT, "not");
    }

    private void visitBinOperator(final OperatorType opType, final String methodName) {
        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, opType)) {
            // swap arguments for regular-expression match operator.
            if (opType == OperatorType.MATCH) {
                this.mv.visitInsn(SWAP);
            }
            loadEnv();
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                    methodName,
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        } else {
            loadEnv();
            loadOpType(opType);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/op/OperationRuntime",
                    "eval",
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;Lcom/googlecode/aviator/lexer/token/OperatorType;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            this.popOperand();
        }
        this.popOperand();
        this.popOperand();
    }

    private void visitUnaryOperator(final OperatorType opType, final String methodName) {
        this.mv.visitTypeInsn(CHECKCAST, "com/googlecode/aviator/runtime/type/BaseAviatorObject");
        loadEnv();

        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, opType)) {
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                    methodName, "(Ljava/util/Map;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        } else {
            loadOpType(opType);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/op/OperationRuntime",
                    "eval",
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;Lcom/googlecode/aviator/lexer/token/OperatorType;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            this.popOperand();
        }


        this.popOperand();
    }


    /**
     * Bit operation '~'
     */
    @Override
    public void onBitNot(final Token<?> lookhead) {
        visitUnaryOperator(OperatorType.BIT_NOT, "bitNot");
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onNeg(com.googlecode.aviator .lexer.token.Token,
     * int)
     */
    @Override
    public void onNeg(final Token<?> lookhead) {
        visitUnaryOperator(OperatorType.NEG, "neg");
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#getResult()
     */
    @Override
    public Expression getResult() {
        end();

        byte[] bytes = this.classWriter.toByteArray();
        try {
            Class<?> defineClass =
                    ClassDefiner.defineClass(this.className, Expression.class, bytes, this.classLoader);
            Constructor<?> constructor =
                    defineClass.getConstructor(AviatorEvaluatorInstance.class, List.class);
            BaseClassExpression exp = (BaseClassExpression) constructor.newInstance(this.instance,
                    new ArrayList<String>(this.varTokens.keySet()));
            exp.setLambdaBootstraps(this.lambdaBootstraps);
            exp.setFuncsArgs(this.funcsArgs);
            return exp;
        } catch (Exception e) {
            if (e.getCause() instanceof ExpressionRuntimeException) {
                throw (ExpressionRuntimeException) e.getCause();
            }
            throw new CompileExpressionErrorException("define class error", e);
        }
    }

    private void end() {
        endVisitMethodCode();
        endVisitClass();
    }


    /**
     * (non-Javadoc)
     *
     * @see com.googlecode.aviator.code.CodeGenerator#onConstant(com.googlecode.aviator
     * .lexer.token.Token)
     */
    @Override
    public void onConstant(final Token<?> lookhead) {
        if (lookhead == null) {
            return;
        }
        // load token to stack
        switch (lookhead.getType()) {
            case Number:
                // load numbers
                NumberToken numberToken = (NumberToken) lookhead;
                Number number = numberToken.getNumber();

                if (TypeUtils.isBigInt(number)) {
                    this.mv.visitLdcInsn(numberToken.getLexeme());
                    this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/type/AviatorBigInt",
                            "valueOf", "(Ljava/lang/String;)Lcom/googlecode/aviator/runtime/type/AviatorBigInt;");
                } else if (TypeUtils.isDecimal(number)) {
                    loadEnv();
                    this.mv.visitLdcInsn(numberToken.getLexeme());
                    this.mv.visitMethodInsn(INVOKESTATIC,
                            "com/googlecode/aviator/runtime/type/AviatorDecimal", "valueOf",
                            "(Ljava/util/Map;Ljava/lang/String;)Lcom/googlecode/aviator/runtime/type/AviatorDecimal;");
                    this.popOperand();
                } else if (TypeUtils.isDouble(number)) {
                    this.mv.visitLdcInsn(number);
                    this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/type/AviatorDouble",
                            "valueOf", "(D)Lcom/googlecode/aviator/runtime/type/AviatorDouble;");
                } else {
                    this.mv.visitLdcInsn(number);
                    this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/type/AviatorLong",
                            "valueOf", "(J)Lcom/googlecode/aviator/runtime/type/AviatorLong;");
                }
                this.pushOperand();
                break;
            case String:
                // load string
                this.mv.visitTypeInsn(NEW, "com/googlecode/aviator/runtime/type/AviatorString");
                this.mv.visitInsn(DUP);
                this.mv.visitLdcInsn(lookhead.getValue(null));
                this.mv.visitMethodInsn(INVOKESPECIAL, "com/googlecode/aviator/runtime/type/AviatorString",
                        "<init>", "(Ljava/lang/String;)V");
                this.pushOperand(3);
                this.popOperand(2);
                break;
            case Pattern:
                // load pattern
                this.mv.visitTypeInsn(NEW, "com/googlecode/aviator/runtime/type/AviatorPattern");
                this.mv.visitInsn(DUP);
                this.mv.visitLdcInsn(lookhead.getValue(null));
                this.mv.visitMethodInsn(INVOKESPECIAL, "com/googlecode/aviator/runtime/type/AviatorPattern",
                        "<init>", "(Ljava/lang/String;)V");
                this.pushOperand(3);
                this.popOperand(2);
                break;
            case Variable:
                // load variable
                Variable variable = (Variable) lookhead;

                if (variable.equals(Variable.TRUE)) {
                    this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean",
                            "TRUE", "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
                    this.pushOperand();
                } else if (variable.equals(Variable.FALSE)) {
                    this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorBoolean",
                            "FALSE", "Lcom/googlecode/aviator/runtime/type/AviatorBoolean;");
                    this.pushOperand();
                } else if (variable.equals(Variable.NIL)) {
                    this.mv.visitFieldInsn(GETSTATIC, "com/googlecode/aviator/runtime/type/AviatorNil", "NIL",
                            "Lcom/googlecode/aviator/runtime/type/AviatorNil;");
                    this.pushOperand();
                } else {
                    String outterVarName = variable.getLexeme();
                    String innerVarName = this.innerVars.get(outterVarName);
                    if (innerVarName != null) {
                        // Is it stored in local?
                        Map<String, Integer> name2Index = this.labelNameIndexMap.get(this.currentLabel);
                        if (name2Index != null && name2Index.get(innerVarName) != null) {
                            int localIndex = name2Index.get(innerVarName);
                            this.mv.visitVarInsn(ALOAD, localIndex);
                            this.pushOperand();
                        } else {
                            // Get field at first time
                            this.mv.visitVarInsn(ALOAD, 0);
                            this.mv.visitFieldInsn(GETFIELD, this.className, innerVarName,
                                    "Lcom/googlecode/aviator/runtime/type/AviatorJavaType;");
                            // Variable is used more than once,store it to local
                            if (this.varTokens.get(outterVarName) > 1) {
                                this.mv.visitInsn(DUP);
                                int localIndex = getLocalIndex();
                                this.mv.visitVarInsn(ASTORE, localIndex);
                                if (name2Index == null) {
                                    name2Index = new HashMap<String, Integer>(100);
                                    this.labelNameIndexMap.put(this.currentLabel, name2Index);
                                }
                                name2Index.put(innerVarName, localIndex);
                                this.pushOperand(3);
                                this.popOperand(2);
                            } else {
                                this.pushOperand(2);
                                this.popOperand();
                            }
                        }

                    } else {
                        this.mv.visitTypeInsn(NEW, "com/googlecode/aviator/runtime/type/AviatorJavaType");
                        this.mv.visitInsn(DUP);
                        this.mv.visitLdcInsn(outterVarName);
                        this.mv.visitMethodInsn(INVOKESPECIAL,
                                "com/googlecode/aviator/runtime/type/AviatorJavaType", "<init>",
                                "(Ljava/lang/String;)V");
                        this.pushOperand(3);
                        this.popOperand(2);
                    }

                }
                break;
            default:
                break;
        }

    }


    public void setLambdaBootstraps(final Map<String, LambdaFunctionBootstrap> lambdaBootstraps) {
        this.lambdaBootstraps = lambdaBootstraps;
    }


    public void initVariables(final Map<String, Integer/* counter */> varTokens) {
        this.varTokens = varTokens;
        this.innerVars = new HashMap<String, String>(varTokens.size());
        for (String outterVarName : varTokens.keySet()) {
            // Use inner variable name instead of outter variable name
            String innerVarName = getInnerName(outterVarName);
            this.innerVars.put(outterVarName, innerVarName);
            this.classWriter.visitField(ACC_PRIVATE + ACC_FINAL, innerVarName,
                    "Lcom/googlecode/aviator/runtime/type/AviatorJavaType;", null, null).visitEnd();

        }
    }


    public void initMethods(final Map<String, Integer/* counter */> methods) {
        this.methodTokens = methods;
        this.innerMethodMap = new HashMap<String, String>(methods.size());
        for (String outterMethodName : methods.keySet()) {
            // Use inner method name instead of outter method name
            String innerMethodName = getInnerName(outterMethodName);
            this.innerMethodMap.put(outterMethodName, innerMethodName);
            this.classWriter.visitField(ACC_PRIVATE + ACC_FINAL, innerMethodName,
                    "Lcom/googlecode/aviator/runtime/type/AviatorFunction;", null, null).visitEnd();
        }
    }


    private String getInnerName(final String varName) {
        return FIELD_PREFIX + this.fieldCounter++;
    }


    private static String getInvokeMethodDesc(final int paramCount) {
        StringBuilder sb = new StringBuilder("(Ljava/util/Map;");
        int number = 20;
        if (paramCount <= number) {
            for (int i = 0; i < paramCount; i++) {
                sb.append("Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            }
        } else {
            for (int i = 0; i < number; i++) {
                sb.append("Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            }
            // variadic params as an array
            sb.append("[Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        }
        sb.append(")Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        return sb.toString();
    }


    @Override
    public void onMethodInvoke(final Token<?> lookhead, final List<FunctionArgument> params) {

        if (this.instance.getOptionValue(Options.CAPTURE_FUNCTION_ARGS).bool) {
            if (params != null && !params.isEmpty()) {
                int funcId = getNextFuncInvocationId();
                getFuncsArgs().put(funcId, Collections.unmodifiableList(params));
                loadEnv();
                this.mv.visitLdcInsn(FUNC_ARGS_INNER_VAR);
                this.mv.visitLdcInsn(funcId);
                this.mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf",
                        "(I)Ljava/lang/Integer;");
                this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put",
                        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                this.mv.visitInsn(POP);
                this.pushOperand(2);
                this.popOperand(3);
                this.pushOperand();
                this.popOperand();
            }
        }

        final MethodMetaData methodMetaData = this.methodMetaDataStack.pop();
        final int parameterCount = methodMetaData.parameterCount;
        int number = 20;
        if (parameterCount >= number) {
            if (parameterCount == number) {
                // pop the list
                this.mv.visitInsn(Opcodes.POP);
                this.popOperand();
            } else {
                // to array
                this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I");
                this.mv.visitTypeInsn(Opcodes.ANEWARRAY,
                        "com/googlecode/aviator/runtime/type/BaseAviatorObject");
                int arrayIndex = getLocalIndex();
                this.mv.visitVarInsn(ASTORE, arrayIndex);
                this.mv.visitVarInsn(ALOAD, methodMetaData.variadicListIndex);
                this.mv.visitVarInsn(ALOAD, arrayIndex);
                this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "toArray",
                        "([Ljava/lang/Object;)[Ljava/lang/Object;");

                this.mv.visitTypeInsn(CHECKCAST, "[Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");

                this.popOperand();
                this.pushOperand(2);
                this.popOperand();
            }
        }
        this.mv.visitMethodInsn(INVOKEINTERFACE, "com/googlecode/aviator/runtime/type/AviatorFunction",
                "call", getInvokeMethodDesc(parameterCount));

        this.popOperand();
        this.popOperand();
        // pop operands
        if (parameterCount <= number) {
            this.popOperand(parameterCount);
        } else {
            // 20 params + one array
            this.popOperand(21);
        }
        // push result
        this.pushOperand();
    }


    @Override
    public void onMethodParameter(final Token<?> lookhead) {
        MethodMetaData currentMethodMetaData = this.methodMetaDataStack.peek();
        int number = 20;
        if (currentMethodMetaData.parameterCount >= number) {
            // Add last param to variadic param list
            assert currentMethodMetaData.variadicListIndex >= 0;
            this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
            this.mv.visitInsn(Opcodes.POP);
            this.mv.visitVarInsn(ALOAD, currentMethodMetaData.variadicListIndex);
            this.popOperand(2);
            this.pushOperand();
            this.popOperand();
            this.pushOperand();
        }

        currentMethodMetaData.parameterCount++;
        if (currentMethodMetaData.parameterCount == number) {
            // create variadic params list for further params
            this.mv.visitTypeInsn(NEW, "java/util/ArrayList");
            this.mv.visitInsn(DUP);
            this.mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
            int listIndex = getLocalIndex();
            this.mv.visitVarInsn(ASTORE, listIndex);
            this.mv.visitVarInsn(ALOAD, listIndex);
            currentMethodMetaData.variadicListIndex = listIndex;
            this.pushOperand(); // new list
        }

        // // add parameter to list
        // this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add",
        // "(Ljava/lang/Object;)Z");
        // // pop boolean
        // this.mv.visitInsn(POP);
        // this.mv.visitVarInsn(ALOAD,
        // this.methodMetaDataStack.peek().parameterListIndex);
    }


    private void pushOperand() {
        this.pushOperand(1);
    }

    private static class MethodMetaData {
        int parameterCount = 0;
        int variadicListIndex = -1;


        public MethodMetaData(final String methodName) {
            super();
        }
    }

    private final Stack<MethodMetaData> methodMetaDataStack = new Stack<MethodMetaData>();


    @Override
    public void onArray(final Token<?> lookhead) {
        onConstant(lookhead);
    }


    @Override
    public void onArrayIndexStart(final Token<?> token) {
        loadEnv();
    }


    @Override
    public void onArrayIndexEnd(final Token<?> lookhead) {
        if (!OperationRuntime.hasRuntimeContext(this.compileEnv, OperatorType.INDEX)) {
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "com/googlecode/aviator/runtime/type/BaseAviatorObject",
                    "getElement",
                    "(Ljava/util/Map;Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
        } else {
            loadOpType(OperatorType.INDEX);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/op/OperationRuntime",
                    "eval",
                    "(Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Ljava/util/Map;Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;Lcom/googlecode/aviator/lexer/token/OperatorType;)Lcom/googlecode/aviator/runtime/type/BaseAviatorObject;");
            this.popOperand();
        }

        this.popOperand(3);
        this.pushOperand();
    }


    public int getLocalIndex() {
        return this.maxLocals++;
    }


    @Override
    public void onLambdaDefineStart(final Token<?> lookhead) {
        if (this.lambdaGenerator == null) {
            this.lambdaGenerator =
                    new LambdaGenerator(this.instance, this, this.parser, this.classLoader);
            this.lambdaGenerator.setScopeInfo(this.parser.enterScope());
        } else {
            throw new CompileExpressionErrorException("Compile lambda error");
        }
    }

    @Override
    public void onLambdaArgument(final Token<?> lookhead) {
        this.lambdaGenerator.addArgument(lookhead.getLexeme());
    }

    @Override
    public void onLambdaBodyStart(final Token<?> lookhead) {
        this.parentCodeGenerator = this.parser.getCodeGenerator();
        this.parser.setCodeGenerator(this.lambdaGenerator);
    }

    @Override
    public void onLambdaBodyEnd(final Token<?> lookhead) {
        this.lambdaGenerator.compileCallMethod();
        LambdaFunctionBootstrap bootstrap = this.lambdaGenerator.getLmabdaBootstrap();
        if (this.lambdaBootstraps == null) {
            this.lambdaBootstraps = new HashMap<String, LambdaFunctionBootstrap>(100);
        }
        this.lambdaBootstraps.put(bootstrap.getName(), bootstrap);
        genNewLambdaCode(bootstrap);
        this.parser.restoreScope(this.lambdaGenerator.getScopeInfo());
        this.lambdaGenerator = null;
        this.parser.setCodeGenerator(this.parentCodeGenerator);
    }


    public void genNewLambdaCode(final LambdaFunctionBootstrap bootstrap) {
        this.mv.visitVarInsn(ALOAD, 0);
        loadEnv();
        this.mv.visitLdcInsn(bootstrap.getName());
        this.mv.visitMethodInsn(INVOKEVIRTUAL, this.className, "newLambda",
                "(Lcom/googlecode/aviator/utils/Env;Ljava/lang/String;)Lcom/googlecode/aviator/runtime/function/BaseLambdaFunction;");
        this.pushOperand(2);
        this.popOperand(2);
    }

    @Override
    public void onMethodName(final Token<?> lookhead) {
        String outtterMethodName = "lambda";
        if (lookhead.getType() != TokenType.Delegate) {
            outtterMethodName = lookhead.getLexeme();
            String innerMethodName = this.innerMethodMap.get(outtterMethodName);
            if (innerMethodName != null) {
                loadAviatorFunction(outtterMethodName, innerMethodName);
            } else {
                createAviatorFunctionObject(outtterMethodName);
            }
        } else {
            loadEnv();
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/RuntimeUtils",
                    "getFunction",
                    "(Ljava/lang/Object;Ljava/util/Map;)Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
            this.popOperand();
        }
        if (this.instance.getOptionValue(Options.TRACE_EVAL).bool) {
            this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/function/TraceFunction",
                    "wrapTrace",
                    "(Lcom/googlecode/aviator/runtime/type/AviatorFunction;)Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
        }
        loadEnv();
        this.methodMetaDataStack.push(new MethodMetaData(outtterMethodName));
    }


    private void loadAviatorFunction(final String outterMethodName, final String innerMethodName) {
        Map<String, Integer> name2Index = this.labelNameIndexMap.get(this.currentLabel);
        // Is it stored in local?
        if (name2Index != null && name2Index.containsKey(innerMethodName)) {
            int localIndex = name2Index.get(innerMethodName);
            this.mv.visitVarInsn(ALOAD, localIndex);
            this.pushOperand();
        } else {
            this.mv.visitVarInsn(ALOAD, 0);
            this.mv.visitFieldInsn(GETFIELD, this.className, innerMethodName,
                    "Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
            // Method is used more than once,store it to local for reusing
            if (this.methodTokens.get(outterMethodName) > 1) {
                this.mv.visitInsn(DUP);
                int localIndex = getLocalIndex();
                this.mv.visitVarInsn(ASTORE, localIndex);
                if (name2Index == null) {
                    name2Index = new HashMap<String, Integer>(100);
                    this.labelNameIndexMap.put(this.currentLabel, name2Index);
                }
                name2Index.put(innerMethodName, localIndex);
                this.pushOperand(2);
                this.popOperand();
            } else {
                this.pushOperand();
            }
        }
    }

    private void loadEnv() {
        // load env
        this.pushOperand();
        this.mv.visitVarInsn(ALOAD, 1);
    }


    private void createAviatorFunctionObject(final String methodName) {
        loadEnv();
        this.pushOperand();
        this.mv.visitLdcInsn(methodName);
        this.mv.visitMethodInsn(INVOKESTATIC, "com/googlecode/aviator/runtime/RuntimeUtils",
                "getFunction",
                "(Ljava/util/Map;Ljava/lang/String;)Lcom/googlecode/aviator/runtime/type/AviatorFunction;");
        this.popOperand(2);
        this.pushOperand();
    }


    @Override
    public void onBitAnd(final Token<?> lookhead) {
        visitBinOperator(OperatorType.BIT_AND, "bitAnd");
    }


    @Override
    public void onBitOr(final Token<?> lookhead) {
        visitBinOperator(OperatorType.BIT_OR, "bitOr");
    }


    @Override
    public void onBitXor(final Token<?> lookhead) {
        visitBinOperator(OperatorType.BIT_XOR, "bitXor");
    }


    @Override
    public void onShiftLeft(final Token<?> lookhead) {
        visitBinOperator(OperatorType.SHIFT_LEFT, "shiftLeft");

    }


    @Override
    public void onShiftRight(final Token<?> lookhead) {
        visitBinOperator(OperatorType.SHIFT_RIGHT, "shiftRight");

    }


    @Override
    public void onUnsignedShiftRight(final Token<?> lookhead) {
        visitBinOperator(OperatorType.U_SHIFT_RIGHT, "unsignedShiftRight");

    }

}
