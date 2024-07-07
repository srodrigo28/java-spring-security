package br.com.treinaweb.twprojects.core.services.http;

import java.util.Optional;

public interface HttpService {

    <T> Optional<T> getPathVariable(String name, Class<T> type);
    
}
