/***
 * ASM: a very small and fast Java bytecode manipulation framework Copyright (c) 2000-2011 INRIA,
 * France Telecom All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met: 1. Redistributions of source code must retain the
 * above copyright notice, this list of conditions and the following disclaimer. 2. Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.googlecode.aviator.asm.commons;

import com.googlecode.aviator.asm.BaseMethodVisitor;
import com.googlecode.aviator.asm.Handle;
import com.googlecode.aviator.asm.Label;
import com.googlecode.aviator.asm.Opcodes;

/**
 * A {@link BaseMethodVisitor} that can be used to approximate method size.
 *
 * @author Eugene Kuleshov
 */
public class CodeSizeEvaluator extends BaseMethodVisitor implements Opcodes {

    private int minSize;

    private int maxSize;

    public CodeSizeEvaluator(final BaseMethodVisitor mv) {
        this(Opcodes.ASM4, mv);
    }

    protected CodeSizeEvaluator(final int api, final BaseMethodVisitor mv) {
        super(api, mv);
    }

    public int getMinSize() {
        return this.minSize;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public void visitInsn(final int opcode) {
        minSize += 1;
        maxSize += 1;
        if (mv != null) {
            mv.visitInsn(opcode);
        }
    }

    @Override
    public void visitIntInsn(final int opcode, final int operand) {
        if (opcode == SIPUSH) {
            minSize += 3;
            maxSize += 3;
        } else {
            minSize += 2;
            maxSize += 2;
        }
        if (mv != null) {
            mv.visitIntInsn(opcode, operand);
        }
    }

    @Override
    public void visitVarInsn(final int opcode, final int var) {
        int number = 4;
        int number256 = 256;
        if (var < number && opcode != RET) {
            minSize += 1;
            maxSize += 1;
        } else if (var >= number256) {
            minSize += 4;
            maxSize += 4;
        } else {
            minSize += 2;
            maxSize += 2;
        }
        if (mv != null) {
            mv.visitVarInsn(opcode, var);
        }
    }

    @Override
    public void visitTypeInsn(final int opcode, final String type) {
        minSize += 3;
        maxSize += 3;
        if (mv != null) {
            mv.visitTypeInsn(opcode, type);
        }
    }

    @Override
    public void visitFieldInsn(final int opcode, final String owner, final String name,
                               final String desc) {
        minSize += 3;
        maxSize += 3;
        if (mv != null) {
            mv.visitFieldInsn(opcode, owner, name, desc);
        }
    }

    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name,
                                final String desc) {
        if (opcode == INVOKEINTERFACE) {
            minSize += 5;
            maxSize += 5;
        } else {
            minSize += 3;
            maxSize += 3;
        }
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, name, desc);
        }
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        minSize += 5;
        maxSize += 5;
        if (mv != null) {
            mv.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        }
    }

    @Override
    public void visitJumpInsn(final int opcode, final Label label) {
        minSize += 3;
        if (opcode == GOTO || opcode == JSR) {
            maxSize += 5;
        } else {
            maxSize += 8;
        }
        if (mv != null) {
            mv.visitJumpInsn(opcode, label);
        }
    }

    @Override
    public void visitLdcInsn(final Object cst) {
        if (cst instanceof Long || cst instanceof Double) {
            minSize += 3;
            maxSize += 3;
        } else {
            minSize += 2;
            maxSize += 3;
        }
        if (mv != null) {
            mv.visitLdcInsn(cst);
        }
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        int number255 = 255;
        int number127 = 127;
        int number128 = -128;
        if (var > number255 || increment > number127 || increment < number128) {
            minSize += 6;
            maxSize += 6;
        } else {
            minSize += 3;
            maxSize += 3;
        }
        if (mv != null) {
            mv.visitIincInsn(var, increment);
        }
    }

    @Override
    public void visitTableSwitchInsn(final int min, final int max, final Label dflt,
                                     final Label... labels) {
        minSize += 13 + labels.length * 4;
        maxSize += 16 + labels.length * 4;
        if (mv != null) {
            mv.visitTableSwitchInsn(min, max, dflt, labels);
        }
    }

    @Override
    public void visitLookupSwitchInsn(final Label dflt, final int[] keys, final Label[] labels) {
        minSize += 9 + keys.length * 8;
        maxSize += 12 + keys.length * 8;
        if (mv != null) {
            mv.visitLookupSwitchInsn(dflt, keys, labels);
        }
    }

    @Override
    public void visitMultiaNewArrayInsn(final String desc, final int dims) {
        minSize += 4;
        maxSize += 4;
        if (mv != null) {
            mv.visitMultiaNewArrayInsn(desc, dims);
        }
    }
}
