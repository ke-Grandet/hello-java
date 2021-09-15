package org.example.provider.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface HelloAnnotation {
    String[] value() default "hello";
}
