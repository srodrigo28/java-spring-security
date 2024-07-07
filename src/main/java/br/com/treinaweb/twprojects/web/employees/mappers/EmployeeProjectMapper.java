package br.com.treinaweb.twprojects.web.employees.mappers;

import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeProjectListItem;

public interface EmployeeProjectMapper {

    EmployeeProjectListItem toEmployeeProjectListItem(Project project);
    
}
