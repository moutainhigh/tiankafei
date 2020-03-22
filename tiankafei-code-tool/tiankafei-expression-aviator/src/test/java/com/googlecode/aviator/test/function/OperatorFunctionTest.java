package com.googlecode.aviator.test.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.googlecode.aviator.runtime.type.BaseAviatorObject;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OperatorFunctionTest {

    @After
    public void tearDown() {
        AviatorEvaluator.newInstance().getOpsMap().clear();
    }

    @Test
    public void testCustomArrayIndexOperator() {
        Map<String, Object> env = new HashMap<>();
        env.put("a", Arrays.asList(4, 5, 6));
        assertEquals(4, AviatorEvaluator.execute("a[0]", env));
        try {
            assertEquals(6, AviatorEvaluator.execute("a[3]", env));
            fail();
        } catch (Exception e) {

        }
        AviatorEvaluator.addOpFunction(OperatorType.INDEX, new AbstractFunction() {

            @Override
            public BaseAviatorObject call(Map<String, Object> env, BaseAviatorObject arg1, BaseAviatorObject arg2) {
                return arg1.getElement(env, arg2.sub(AviatorLong.valueOf(1), env));
            }

            @Override
            public String getName() {
                return "[]";
            }
        });

        assertEquals(6, AviatorEvaluator.execute("a[3]", env));
        assertEquals(5, AviatorEvaluator.execute("a[2]", env));
        assertEquals(4, AviatorEvaluator.execute("a[1]", env));
        try {
            assertEquals(4, AviatorEvaluator.execute("a[0]", env));
            fail();
        } catch (Exception e) {

        }
    }

    @Test(expected = Exception.class)
    public void testCustomUnaryOperatorFunction() {
        try {
            assertEquals("3", AviatorEvaluator.execute("!3"));
            fail();
        } catch (Exception e) {
        }
        AviatorEvaluator.addOpFunction(OperatorType.NOT, new AbstractFunction() {

            @Override
            public BaseAviatorObject call(Map<String, Object> env, BaseAviatorObject arg1) {
                return new AviatorString(arg1.getValue(env).toString());
            }

            @Override
            public String getName() {
                return "!";
            }
        });

        assertEquals("3", AviatorEvaluator.execute("!3"));

        assertEquals("4", AviatorEvaluator.newInstance().exec("!a", 4));
        assertEquals("3.2", AviatorEvaluator.execute("!3.2"));
    }

    @Test(expected = AssertionError.class)
    public void testCustomBinOperatorFunction() {
        try {
            assertEquals("hello world", AviatorEvaluator.execute("'hello' & ' world'"));
            fail();
        } catch (Exception e) {
        }
        AviatorEvaluator.addOpFunction(OperatorType.BIT_AND, new AbstractFunction() {

            @Override
            public BaseAviatorObject call(Map<String, Object> env, BaseAviatorObject arg1, BaseAviatorObject arg2) {
                return new AviatorString(arg1.getValue(env).toString() + arg2.getValue(env).toString());
            }

            @Override
            public String getName() {
                return "&";
            }
        });

        assertEquals("43", AviatorEvaluator.newInstance().exec("a&3", 4));
        assertEquals("43", AviatorEvaluator.newInstance().exec("4&3", 4));
        assertEquals("hello world", AviatorEvaluator.execute("'hello' & ' world'"));
        assertEquals("hello world", AviatorEvaluator.newInstance().exec("a&' world'", "hello"));
        assertEquals("hello3 world", AviatorEvaluator.newInstance().exec("a & 3 & ' world'", "hello"));
        Map<String, Object> env = new HashMap<>();
        env.put("list", Arrays.asList(1, 2, 3));
        assertEquals("123", AviatorEvaluator.execute("reduce(list, &, '')", env));
    }
}
