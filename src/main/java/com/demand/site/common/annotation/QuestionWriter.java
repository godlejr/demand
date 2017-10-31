package com.demand.site.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionWriterValidator.class)
public @interface QuestionWriter {
	String message() default "작성자명은 한/영(숫자 혼용가능) 10자 이내로 입력해주세요.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}