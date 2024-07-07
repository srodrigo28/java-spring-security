package br.com.treinaweb.twprojects.web.clients.dtos;

import br.com.treinaweb.twprojects.core.validators.ClientEmailUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class ClientForm {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @ClientEmailUnique
    private String email;

    @NotEmpty
    @Size(min = 15, max = 15)
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "deve estar no formato (00) 00000-0000")
    private String phone;
    
}
