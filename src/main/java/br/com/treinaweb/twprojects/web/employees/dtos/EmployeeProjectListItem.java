package br.com.treinaweb.twprojects.web.employees.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectListItem {

    private Long id;

    private String name;

    private String description;
    
}
