package com.nnniu.bs.ch3;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
	
	@Override
	public void initialize(MyConstraint myConstraint) {
		// 启动时执行
		System.out.println("MyConstraintValidator initialize");
	}
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println("s: " + s);
		if (!s.equals("MAIL"))
			return false;
		
		return true;
	}
	
}
