package br.com.treinaweb.twprojects.core.exceptions;

public class EmployeeNotFoundException extends ModelNotFoundException {

    public EmployeeNotFoundException() {
        super("Funcionário não encontrado");
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
    
}
