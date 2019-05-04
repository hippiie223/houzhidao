package com.hippie.houzhidao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 39239
 * @Date 2019/4/29 15:15
 * @Package com.hippie.houzhidao.util
 * @Description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdateTime {
    String value() default "";
}
