package br.com.treinaweb.twprojects.core.validators;

import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ResignationDateGreatherThanHireDateValidator implements 
    ConstraintValidator<ResignationDateGreatherThanHireDate, EmployeeForm> {

    private String message;

    @Override
    public boolean isValid(EmployeeForm value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value.getHireDate() == null || value.getResignationDate() == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode("resignationDate")
            .addConstraintViolation();

        return value.getResignationDate().isAfter(value.getHireDate());
    }

    @Override
    public void initialize(ResignationDateGreatherThanHireDate constraintAnnotation) {
        message = constraintAnnotation.message();
    }
    
}
