package br.com.treinaweb.twprojects.web.clients.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Client;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientForm;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "modelmapper")
public class ModelMapperClientMapper implements ClientMapper {

    private final ModelMapper modelMapper;

    @Override
    public Client toClient(ClientForm clientForm) {
        return modelMapper.map(clientForm, Client.class);
    }

    @Override
    public ClientForm toClientForm(Client client) {
        return modelMapper.map(client, ClientForm.class);
    }

    @Override
    public ClientListItem toClientListItem(Client client) {
        return modelMapper.map(client, ClientListItem.class);
    }
    
}
