package br.com.treinaweb.twprojects.web.projects.mappers;

import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectTeamListItem;

public interface ProjectTeamMapper {

    ProjectTeamListItem toProjectTeamListItem(Employee employee);
    
}
