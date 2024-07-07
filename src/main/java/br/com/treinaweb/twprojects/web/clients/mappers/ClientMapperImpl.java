package br.com.treinaweb.twprojects.web.clients.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Client;
import br.com.treinaweb.twprojects.core.utils.StringUtils;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientForm;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientListItem;

@Component
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toClient(ClientForm clientForm) {
        return Client.builder()
            .name(clientForm.getName())
            .email(clientForm.getEmail())
            .phone(StringUtils.cleanPhone(clientForm.getPhone()))
            .build();
    }

    @Override
    public ClientForm toClientForm(Client client) {
        return ClientForm.builder()
            .name(client.getName())
            .email(client.getEmail())
            .phone(StringUtils.formatPhone(client.getPhone()))
            .build();
    }

    @Override
    public ClientListItem toClientListItem(Client client) {
        return ClientListItem.builder()
            .id(client.getId())
            .name(client.getName())
            .email(client.getEmail())
            .phone(client.getPhone())
            .build();
    }
    
}
