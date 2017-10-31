package com.demand.site.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionPasswordValidator.class)
public @interface QuestionPassword {
     String message() default
      "비밀번호는 특수문자 제외, 18자 이내로 입력해주세요.";
      
      Class<?>[] groups() default {};
      Class<? extends Payload>[] payload() default {};
}