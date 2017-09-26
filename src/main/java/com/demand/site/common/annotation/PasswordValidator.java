package com.demand.site.common.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator  implements ConstraintValidator<Password, String>{
	   @Override
	   public void initialize(Password arg0) {
	      
	   }

	   @Override
	   public boolean isValid(String password, ConstraintValidatorContext arg1) {
	      return validate(password);
	   }
	   
	   public boolean validate(String password){
	      if(password != null){
	         String regex = "((?=.*[a-z])(?=.*[0-9]).{8,15})";
	         Pattern pattern = Pattern.compile(regex);
	         Matcher matcher = pattern.matcher(password);
	         
	         return matcher.matches();
	      } else { 
	         return false; 
	      }
	   }
	}