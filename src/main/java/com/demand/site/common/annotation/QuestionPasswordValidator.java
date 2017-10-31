package com.demand.site.common.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuestionPasswordValidator implements ConstraintValidator<QuestionPassword, String> {

   @Override
   public void initialize(QuestionPassword constraintAnnotation) {

   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      return validate(value);
   }

   public boolean validate(String password) {
      if (password != null) {
         String regex = "((?=.*[0-9]).{1,18})";
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(password);

         return matcher.matches();
      } else {
         return false;
      }
   }

}