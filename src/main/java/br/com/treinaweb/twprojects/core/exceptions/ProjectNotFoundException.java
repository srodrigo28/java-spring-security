package br.com.treinaweb.twprojects.core.exceptions;

public class ProjectNotFoundException extends ModelNotFoundException {

    public ProjectNotFoundException() {
        super("Projeto não encontrado");
    }
    
    public ProjectNotFoundException(String message) {
        super(message);
    }
    
}
