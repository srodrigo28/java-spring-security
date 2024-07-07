package br.com.treinaweb.twprojects.core.validators;

import br.com.treinaweb.twprojects.core.repositories.ClientRepository;
import br.com.treinaweb.twprojects.core.services.http.HttpService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientEmailUniqueValidator implements ConstraintValidator<ClientEmailUnique, String> {

    private final HttpService httpService;
    private final ClientRepository clientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var id = httpService.getPathVariable("id", Long.class);

        if (id.isPresent()) {
            return clientRepository.findByEmailAndIdNot(value, id.get()).isEmpty();
        }

        return clientRepository.findByEmail(value).isEmpty();
    }
    
}
