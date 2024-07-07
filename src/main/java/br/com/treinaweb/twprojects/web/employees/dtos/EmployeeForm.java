package br.com.treinaweb.twprojects.web.employees.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.treinaweb.twprojects.core.validators.Age;
import br.com.treinaweb.twprojects.core.validators.Comparison;
import br.com.treinaweb.twprojects.core.validators.FieldsComparison;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldsComparison(
    field = "hireDate",
    fieldToCompare = "resignationDate",
    comparison = Comparison.LESS_THAN,
    message = "a data de demissão deve ser maior que a data de contratação",
    fieldError = "resignationDate"
)
public class EmployeeForm {

    @NotEmpty
    @Size(min = 3, max = 100)
    private String name;

    @Email
    @NotEmpty
    @Size(max = 255)
    private String email;

    @NotEmpty
    @Size(min = 15, max = 15)
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "deve estar no formato (00) 00000-0000")
    private String phone;

    @CPF
    @NotEmpty
    @Size(min = 14, max = 14)
    private String cpf;

    @Past
    @NotNull
    @Age(min = 18, max = 100)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthDate;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate hireDate;

    @PastOrPresent
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate resignationDate;

    @Valid
    private AddressForm address;

    @NotNull
    @Positive
    private Long positionId;
    
}
