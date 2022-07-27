package ml.ytooo.annotation.advice;

import ml.ytooo.annotation.ResultFormat;
import ml.ytooo.annotation.interceptor.ResultForamtInterceptor;
import ml.ytooo.http.Response;
import ml.ytooo.http.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * @author you.minda
 * @date 2022/4/13 09:37
 */
@Component
public class ResultForamtAdvice implements ResponseBodyAdvice<Response> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        final HttpServletRequest request = attributes.getRequest();
//        final Object attribute = request.getAttribute(ResultForamtInterceptor.RESULT_FORMAT_KEY);
//        if (attribute != null && attribute instanceof Annotation) {
////            final Class<?> clazz = ((ResultFormat) attribute).value();
////            try {
////                final Object o = clazz.getDeclaredConstructor().newInstance();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
        return false;
    }

    @Override
    public Response beforeBodyWrite(Response body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return Result.success(body);
    }

}
