package br.com.treinaweb.twprojects.core.validators;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("unchecked")
public class FieldsComparisonValidator implements ConstraintValidator<FieldsComparison, Object> {

    private String field;
    private String fieldToCompare;
    private String fieldError;
    private Comparison comparison;
    private String message;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode(fieldError)
            .addConstraintViolation();

        var field1 = BeanUtils.getPropertyDescriptor(value.getClass(), field);
        var field2 = BeanUtils.getPropertyDescriptor(value.getClass(), fieldToCompare);

        if (field1 == null || field2 == null) {
            throw new IllegalArgumentException("Os campos devem existir na classe");
        }

        try {
            var value1 = field1.getReadMethod().invoke(value);
            var value2 = field2.getReadMethod().invoke(value);

            if (value1 == null || value2 == null) {
                return true;
            }

            if (!(value1 instanceof Comparable) || !(value2 instanceof Comparable)) {
                throw new IllegalArgumentException("Os campos devem implementar a interface Comparable");
            }

            if (value1.getClass() != value2.getClass()) {
                throw new IllegalArgumentException("Os campos devem ser do mesmo tipo");
            }

            var comparable1 = (Comparable<Object>) value1;
            var comparable2 = (Comparable<Object>) value2;
            var comparsionResult = comparable1.compareTo(comparable2);

            switch (comparison) {
                case GREATER_THAN:
                    return comparsionResult > 0;
                case GREATER_THAN_OR_EQUAL_TO:
                    return comparsionResult >= 0;
                case LESS_THAN:
                    return comparsionResult < 0;
                case LESS_THAN_OR_EQUAL_TO:
                    return comparsionResult <= 0;
                default:
                    return comparsionResult == 0;
            }
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Os campos devem ser acessíveis");
        }
    }

    @Override
    public void initialize(FieldsComparison constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldToCompare = constraintAnnotation.fieldToCompare();
        fieldError = constraintAnnotation.fieldError();
        comparison = constraintAnnotation.comparison();
        message = constraintAnnotation.message();
    }
    
}
