package br.com.treinaweb.twprojects.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Age {

    String message() default "a idade deve estar entre {min} e {max}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

    int min() default 0;

	int max() default Integer.MAX_VALUE;
    
}
