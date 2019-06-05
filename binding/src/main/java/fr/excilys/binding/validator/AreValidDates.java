package fr.excilys.binding.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DatesValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AreValidDates {
	String message() default "Dates are invalid.";

	String introduced();

	String discontinued();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
