package cn.tiankafei.aviator.extend.function;

import cn.tiankafei.aviator.extend.constant.FunctionConstants;
import cn.tiankafei.aviator.extend.exception.AviatorException;
import cn.tiankafei.aviator.extend.util.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.List;
import java.util.Map;

/**
 * @Author 魏双双
 * @Date 2020/6/2
 * @Version V1.0
 **/
public class InRange extends MoreParamFunction {
    @Override
    public String getName() {
        return FunctionConstants.INRANGE;
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        return super.call(env, arg1, arg2, arg3);
    }

    @Override
    public AviatorObject apply(List<Object> dataList) {
        int length = dataList.size();
        int number = 3;
        if (length == number) {
            Object text = dataList.get(0);
            Object beginTxt = dataList.get(1);
            Object endTxt = dataList.get(2);

            if (text != null && beginTxt != null && endTxt != null) {
                if (FunctionUtils.isString(text) || FunctionUtils.isString(beginTxt) || FunctionUtils.isString(endTxt)) {
                    //只要有一个是字符串的时候
                    if (FunctionUtils.isNumerics(text.toString())
                            && FunctionUtils.isNumerics(beginTxt.toString())
                            && FunctionUtils.isNumerics(endTxt.toString())) {
                        //全部为数字时，走数字的比较
                        return AviatorBoolean.valueOf(Double.parseDouble(text.toString()) >= Double.parseDouble(beginTxt.toString()) && Double.parseDouble(text.toString()) <= Double.parseDouble(endTxt.toString()));
                    } else {
                        //部分为数字时，走字符串的比较
                        String strText = text.toString();
                        String strBeginTxt = beginTxt.toString();
                        String strEndTxt = endTxt.toString();
                        return AviatorBoolean.valueOf((strText).compareTo(strBeginTxt) >= 0 && (strText).compareTo(strEndTxt) <= 0);
                    }
                }
                if (text instanceof Number && beginTxt instanceof Number && endTxt instanceof Number) {
                    return AviatorBoolean.valueOf(((Number) text).doubleValue() >= ((Number) beginTxt).doubleValue() && ((Number) text).doubleValue() <= ((Number) endTxt).doubleValue());
                }
            }
            return AviatorBoolean.FALSE;
        }
        throw new AviatorException(getName() + "传入参数数组为空或者参数个数不正确!");
    }
}
