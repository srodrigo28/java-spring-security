package br.com.treinaweb.twprojects.web.employees.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Address;
import br.com.treinaweb.twprojects.core.utils.StringUtils;
import br.com.treinaweb.twprojects.web.employees.dtos.AddressForm;

@Component
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressForm toAddressForm(Address address) {
        return AddressForm.builder()
            .city(address.getCity())
            .state(address.getState())
            .street(address.getStreet())
            .number(address.getNumber())
            .complement(address.getComplement())
            .zipCode(StringUtils.formatZipCode(address.getZipCode()))
            .neighborhood(address.getNeighborhood())
            .build();
    }

    @Override
    public Address toAddress(AddressForm addressForm) {
        return Address.builder()
            .city(addressForm.getCity())
            .state(addressForm.getState())
            .street(addressForm.getStreet())
            .number(addressForm.getNumber())
            .complement(addressForm.getComplement())
            .zipCode(StringUtils.cleanZipCode(addressForm.getZipCode()))
            .neighborhood(addressForm.getNeighborhood())
            .build();
    }

    @Override
    public String formatAddress(Address address) {
        return String.format(
            "%s, %s - %s - %s - %s",
            address.getStreet(),
            address.getNumber(),
            address.getNeighborhood(),
            address.getCity(),
            address.getState()
        );
    }
    
}
