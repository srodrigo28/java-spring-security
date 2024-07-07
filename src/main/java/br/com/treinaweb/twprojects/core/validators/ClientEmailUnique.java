package br.com.treinaweb.twprojects.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClientEmailUniqueValidator.class)
public @interface ClientEmailUnique {

    String message() default "o email informado já existe";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
    
}
