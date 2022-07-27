package ml.ytooo.annotation;

import ml.ytooo.http.Response;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author you.minda
 * @date 2022/4/12 19:38
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface ResultFormat {
//    Class<?> value() default Response.class;
}
