package com.demand.site.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)

public @interface Password {
   String message() default
   "영문, 숫자 조합으로 8~15자로 입력해주세요.";
   
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}