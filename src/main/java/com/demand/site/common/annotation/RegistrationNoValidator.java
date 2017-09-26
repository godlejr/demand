package com.demand.site.common.annotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationNoValidator implements ConstraintValidator<RegistrationNo, String> {

	   @Override
	   public void initialize(RegistrationNo arg0) {

	   }

	   @Override
	   public boolean isValid(String registration, ConstraintValidatorContext arg1) {
	      return validate(registration);
	   }

	   public boolean validate(String registration) {
	      String regex = "\\d{6}[1234]\\d{6}";
	      if (!registration.matches(regex)) {
	         return false;
	      }

	      try {
	         String strDate = registration.substring(0, 6);
	         strDate = ((registration.charAt(6) == '1' || registration.charAt(6) == '2') ? "19" : "20") + strDate;
	         strDate = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/" + strDate.substring(6, 8);

	         SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
	         Date date = dateformat.parse(strDate);
	         String resultStr = dateformat.format(date);

	         if (!resultStr.equalsIgnoreCase(strDate)) {
	            return false;
	         }

	      } catch (ParseException e) {
	         e.printStackTrace();
	         return false;
	      }

	      char[] charArray = registration.toCharArray();
	      long sum = 0;
	      int[] arrDivide = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 };
	      for (int i = 0; i < charArray.length - 1; i++) {
	         sum += Integer.parseInt(String.valueOf(charArray[i])) * arrDivide[i];
	      }

	      int checkdigit = (int) ((int) (11 - sum % 11)) % 10;
	      return (checkdigit == Integer.parseInt(String.valueOf(charArray[12]))) ? true : false;

	   }

	}