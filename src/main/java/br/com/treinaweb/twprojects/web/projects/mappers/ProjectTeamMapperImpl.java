package br.com.treinaweb.twprojects.web.projects.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectTeamListItem;

@Component
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class ProjectTeamMapperImpl implements ProjectTeamMapper {

    @Override
    public ProjectTeamListItem toProjectTeamListItem(Employee employee) {
        return ProjectTeamListItem.builder()
            .name(employee.getName())
            .position(employee.getPosition().getName())
            .build();
    }
    
}
