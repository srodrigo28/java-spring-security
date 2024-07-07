package br.com.treinaweb.twprojects.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsComparisonValidator.class)
public @interface FieldsComparison {

    String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

    String field();

    String fieldToCompare();

    String fieldError() default "global";

    Comparison comparison();
    
}
