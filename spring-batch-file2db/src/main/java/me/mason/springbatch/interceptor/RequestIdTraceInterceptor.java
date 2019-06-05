package me.mason.springbatch.interceptor;

import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 添加request-id
 * @Author Mason
 * @Since 2019/4/8
 **/
@Component
public class RequestIdTraceInterceptor implements HandlerInterceptor  {

    public static final String REQUEST_ID_KEY = "x-request-id";
    public static ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<String>();

    private static final Logger logger = LoggerFactory.getLogger(RequestIdTraceInterceptor.class);

    public static String getRequestId(HttpServletRequest request) {
        String requestId;
        String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
        String headerRequestId = request.getHeader(REQUEST_ID_KEY);

        if (parameterRequestId == null && headerRequestId == null) {
            logger.info("request parameter 和header 都没有requestId入参");
            requestId = IdUtil.simpleUUID();
        } else {
            requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
        }

        requestIdThreadLocal.set(requestId);

        return requestId;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = getRequestId(request);
        MDC.put(REQUEST_ID_KEY, requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestId=requestIdThreadLocal.get();
        response.addHeader(REQUEST_ID_KEY, requestId);
        requestIdThreadLocal.remove();
        MDC.remove(REQUEST_ID_KEY);
    }
}
