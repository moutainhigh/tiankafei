package com.googlecode.aviator.example;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.RuntimeUtils;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.BaseAviatorNumber;
import com.googlecode.aviator.runtime.type.BaseAviatorObject;

import java.math.BigDecimal;
import java.util.Map;

public class OperatorOverloadExample {

    /**
     * Overload divide operator, when the dividend is zero,return 0.
     *
     * @author boyan(boyan @ antfin.com)
     */
    public static class OverloadDivide extends AbstractFunction {

        @Override
        public String getName() {
            return "/";
        }

        @Override
        public BaseAviatorObject call(final Map<String, Object> env, final BaseAviatorObject arg1,
                                      final BaseAviatorObject arg2) {
            if (FunctionUtils.getNumberValue(arg2, env).doubleValue() == 0) {
                return new AviatorDecimal(0);
            }
            BigDecimal left = BaseAviatorNumber.valueOf(arg1.getValue(env)).toDecimal(env);
            BigDecimal right = BaseAviatorNumber.valueOf(arg2.getValue(env)).toDecimal(env);
            return AviatorDecimal.valueOf(left.divide(right, RuntimeUtils.getMathContext(env)));
        }

    }

    public static void main(final String[] args) {
        AviatorEvaluator.addOpFunction(OperatorType.DIV, new OverloadDivide());

        System.out.println(AviatorEvaluator.execute("4 - 1/0 + 3"));
        System.out.println(AviatorEvaluator.execute("4 - 1/2.0 + 3"));
    }
}
