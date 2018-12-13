package com.nnniu.bs.ch3;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	String message() default "自定义Validator注解校验失败";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
