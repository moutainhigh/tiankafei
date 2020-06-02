package cn.tiankafei.aviator.extend.function;

import cn.tiankafei.aviator.extend.constant.FunctionConstants;
import cn.tiankafei.aviator.extend.exception.AviatorException;
import cn.tiankafei.aviator.extend.util.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author 魏双双
 * @Date 2020/6/2
 * @Version V1.0
 **/
public class Year extends MoreParamFunction {
    @Override
    public String getName() {
        return FunctionConstants.YEAR;
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        return super.call(env, arg1, arg2, arg3);
    }

    @Override
    public AviatorObject apply(List<Object> dataList) {
        int length = dataList.size();
        int number3 = 3;
        if (length == number3) {
            Object yearObj = dataList.get(0);
            Object currentBgqObj = dataList.get(1);
            Object tagObj = dataList.get(2);
            boolean flag = yearObj != null && StringUtils.isNotBlank(yearObj.toString())
                    && currentBgqObj != null && StringUtils.isNotBlank(currentBgqObj.toString())
                    && tagObj != null && StringUtils.isNotBlank(tagObj.toString());
            if (!flag) {
                return AviatorBoolean.FALSE;
            }
            String currentBgq = dataList.get(1).toString();
            String endTag = dataList.get(2).toString();

            String yearStr = "";
            if (FunctionUtils.isNumerics(yearObj.toString())) {
                yearStr = Double.valueOf(yearObj.toString()).intValue() + "";
            } else {
                return AviatorBoolean.FALSE;
            }
            String currentYear = "";
            if (currentBgq.length() > 4) {
                currentYear = currentBgq.substring(0, 4);
            } else {
                return AviatorBoolean.FALSE;
            }
            int year = Integer.valueOf(yearStr);
            if ("0".equals(endTag)) {
                return AviatorBoolean.valueOf((year - 1800 >= 0) && (Integer.valueOf(currentYear) - year >= 0));
            } else if ("1".equals(endTag)) {
                return AviatorBoolean.valueOf(year - 1800 >= 0);
            } else if ("2".equals(endTag)) {
                return AviatorBoolean.valueOf(year - Integer.valueOf(currentYear) >= 0);
            } else {
                return AviatorBoolean.FALSE;
            }
        }
        throw new AviatorException(getName() + "传入参数数组为空或者参数个数不正确!");
    }
}
