package br.com.treinaweb.twprojects.web.employees.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeProjectListItem;

@Component
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class EmployeeProjectMapperImpl implements EmployeeProjectMapper {

    @Override
    public EmployeeProjectListItem toEmployeeProjectListItem(Project project) {
        return EmployeeProjectListItem.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .build();
    }
    
}
