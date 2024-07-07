package br.com.treinaweb.twprojects.web.employees.dtos;

import br.com.treinaweb.twprojects.core.enums.AddressState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressForm {

    @NotBlank
    @Size(min = 3, max = 100)
    private String city;
    
    @NotNull
    private AddressState state;
    
    @NotNull
    @Size(min = 3, max = 100)
    private String street;
    
    @NotBlank
    @Size(min = 1, max = 10)
    private String number;

    @Size(max = 100)
    private String complement;
    
    @NotBlank
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{5}-\\d{3}$")
    private String zipCode;
    
    @NotEmpty
    @Size(min = 3, max = 100)
    private String neighborhood;
    
}
