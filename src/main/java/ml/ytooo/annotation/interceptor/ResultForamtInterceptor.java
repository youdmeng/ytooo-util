package ml.ytooo.annotation.interceptor;


import ml.ytooo.annotation.ResultFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author you.minda
 * @date 2022/4/12 19:58
 */
public class ResultForamtInterceptor implements HandlerInterceptor {

    public final static String RESULT_FORMAT_KEY = "resultFormatAnnotation";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(ResultFormat.class) || handlerMethod.getMethod().isAnnotationPresent(ResultFormat.class)) {
                request.setAttribute(RESULT_FORMAT_KEY, handlerMethod.getMethodAnnotation(ResultFormat.class));
            }
        }
        return true;
    }
}
