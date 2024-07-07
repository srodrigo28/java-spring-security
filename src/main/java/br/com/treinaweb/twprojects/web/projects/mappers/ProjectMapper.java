package br.com.treinaweb.twprojects.web.projects.mappers;

import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectDetails;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectForm;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectListItem;

public interface ProjectMapper {

    ProjectListItem toProjectListItem(Project project);

    ProjectDetails toProjectDetails(Project project);

    Project toProject(ProjectForm projectForm);

    ProjectForm toProjectForm(Project project);
    
}
