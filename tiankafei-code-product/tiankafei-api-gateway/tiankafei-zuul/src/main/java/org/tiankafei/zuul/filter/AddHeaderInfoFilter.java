package org.tiankafei.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.tiankafei.web.common.utils.SequenceUtil;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AddHeaderInfoFilter extends ZuulFilter {
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        String value = SequenceUtil.generatorStrId();
        log.info("执行了添加header信息的过滤器：添加的header值为：{}", value);
        response.setHeader("TestHeaderInfo", value);

        return null;
    }
}
