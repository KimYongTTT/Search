package com.example.search.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    long requestStartTime = 0;
    long requestEndTime = 0;
    long elapseTime = 0;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        requestStartTime = System.currentTimeMillis();
        if (request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper contentCachingRequestWrapper =
                    (ContentCachingRequestWrapper) request;
            String requestBody =
                    IOUtils.toString(
                            contentCachingRequestWrapper.getInputStream(),
                            contentCachingRequestWrapper.getCharacterEncoding());

            log.info(
                    "Method : {} , Uri : {} , Query String : {} , Request Body : {}",
                    contentCachingRequestWrapper.getMethod(),
                    contentCachingRequestWrapper.getRequestURI(),
                    contentCachingRequestWrapper.getQueryString(),
                    requestBody);
        }
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        requestEndTime = System.currentTimeMillis();
        elapseTime = requestEndTime - requestStartTime;
        if (response instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper contentCachingResponseWrapper =
                    (ContentCachingResponseWrapper) response;
            String responseLog =
                    IOUtils.toString(
                            contentCachingResponseWrapper.getContentInputStream(),
                            response.getCharacterEncoding());

            log.info(
                    "Elapse Time : {} , Http Status Code : {} , Response Body : {}",
                    elapseTime,
                    response.getStatus(),
                    responseLog);
        }
    }
}
