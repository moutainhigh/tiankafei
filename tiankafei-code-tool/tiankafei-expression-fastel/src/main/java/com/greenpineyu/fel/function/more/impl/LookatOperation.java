package com.greenpineyu.fel.function.more.impl;

import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.function.api.BaseMoreOperation;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式函数:根据正则表达式模式，判断文本字符串是否从开始处匹配
 * 语法
 * LOOKAT(text_pattern,text_matcher)
 * text_pattern 正则表达式模式
 * text_matcher 文本字符串
 *
 * @author tiankafei
 */
public class LookatOperation extends BaseMoreOperation {

    private LookatOperation() {
    }

    private static class InternalClass {
        private final static BaseMoreOperation INSTANCE = new LookatOperation();
    }

    public static BaseMoreOperation getInstance() {
        return InternalClass.INSTANCE;
    }

    @Override
    public Object evl(List<Object> dataList, FelContext context, int location) throws Exception {
        int length = dataList.size();
        int number2 = 2;
        if (length == number2) {
            try {
                Object pattern = dataList.get(0);
                Object matcher = dataList.get(1);
                if (pattern == null || matcher == null) {
                    return Boolean.FALSE;
                }
                Pattern p = Pattern.compile(pattern.toString());
                Matcher m = p.matcher(matcher.toString());
                return Boolean.valueOf(m.lookingAt());
            } catch (Exception e) {
                throw new Exception("LOOKAT函数的参数类型不合法!");
            }
        }
        throw new AviatorException("传入参数数组为空或者参数个数不正确!");
    }

}
