package br.com.treinaweb.twprojects.web.auth.dtos;

import br.com.treinaweb.twprojects.core.validators.Comparison;
import br.com.treinaweb.twprojects.core.validators.FieldsComparison;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldsComparison(
    field = "newPassword",
    fieldToCompare = "newPasswordConfirmation",
    fieldError = "newPasswordConfirmation",
    message = "as senhas não conferem",
    comparison = Comparison.EQUAL_TO
)
public class UpdatePasswordForm {

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String newPasswordConfirmation;
    
}
