package com.demand.site.common.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuestionWriterValidator implements ConstraintValidator<QuestionWriter, String> {

	@Override
	public void initialize(QuestionWriter constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return validate(value);
	}

	public boolean validate(String writer) {
		if (writer != null) {
			String regex = "((?=.*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+.*).{1,10})";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(writer);

			return matcher.matches();
		} else {
			return false;
		}
	}

}