package br.com.treinaweb.twprojects.web.clients.mappers;

import br.com.treinaweb.twprojects.core.models.Client;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientForm;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientListItem;

public interface ClientMapper {
    
    Client toClient(ClientForm clientForm);

    ClientForm toClientForm(Client client);

    ClientListItem toClientListItem(Client client);

}
