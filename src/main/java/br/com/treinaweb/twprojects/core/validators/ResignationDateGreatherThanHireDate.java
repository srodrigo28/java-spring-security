package br.com.treinaweb.twprojects.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ResignationDateGreatherThanHireDateValidator.class)
public @interface ResignationDateGreatherThanHireDate {

    String message() default "a data de demissão deve ser posterior a data de contratação";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
    
}
