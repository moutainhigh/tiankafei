package org.tiankafei.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.tiankafei.web.common.api.ApiResult;
import org.tiankafei.web.common.enums.ExceptionEnum;
import org.tiankafei.web.common.utils.CommonUtil;
import org.tiankafei.zuul.utils.ZuulUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tiankafei
 * @since 1.0
 **/
@Slf4j
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 如果为false,说明该url不需要过滤，则直接返回
        boolean needFilterFlag = super.shouldFilter();
        if(!needFilterFlag){
            return Boolean.FALSE;
        }

        List<String> authUrls = exclusionsUrlsProperties.getAuthUrls();

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        this.currentPath = request.getServletPath();

        boolean needAuthFlag = CommonUtil.checkUrlStartsWith(authUrls, currentPath);
        if(needAuthFlag){
            log.info("不需要鉴权的url：{}", currentPath);
            return false;
        }
        return true;
    }

    /**
     *  1.从请求参数中拿到用户id
     *  2.根据用户id去缓存中获取用户信息
     *  3.如果为空，则返回失败的数据
     *  4.如果不为空，则继续下一个过滤
     *
     * @return
     */
    @Override
    public Object run() throws ZuulException {
        //TODO 基于zuul的鉴权在这里执行，鉴权成功返回true，失败返回false
//        boolean flag = Boolean.FALSE;
        boolean flag = RandomUtils.nextBoolean();
        if(flag){
            // 鉴权通过
            log.info("正在执行鉴权，鉴权通过的url：{}", currentPath);
        }else{
            // 鉴权失败
            log.error("正在执行鉴权，鉴权没有通过的url：{}", currentPath);
            //返回错误提示内容
            ApiResult error = ApiResult.error(ExceptionEnum.LOGIN_AUTHENTICATION_EXCEPTION);
            ZuulUtil.returnValue(error, httpProperties.getEncoding().getCharset());

//            // 之所以要抛异常，是为了阻止执行下一个filter
//            throw new ZuulException(JSON.toJSONString(error), error.getStatus(), error.getMessage());
        }
        return null;
    }

}