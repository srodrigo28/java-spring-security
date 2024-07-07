package br.com.treinaweb.twprojects.web.employees.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails {

    private String name;

    private String email;

    private String phone;

    private String cpf;

    private LocalDate birthDate;

    private LocalDate hireDate;

    private LocalDate resignationDate;

    private String address;

    private String position;

    private List<EmployeeProjectListItem> projects;
    
}
