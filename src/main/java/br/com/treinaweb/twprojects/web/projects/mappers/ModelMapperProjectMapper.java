package br.com.treinaweb.twprojects.web.projects.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectDetails;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectForm;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "modelmapper")
public class ModelMapperProjectMapper implements ProjectMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProjectListItem toProjectListItem(Project project) {
        return modelMapper.map(project, ProjectListItem.class);
    }

    @Override
    public ProjectDetails toProjectDetails(Project project) {
        return modelMapper.map(project, ProjectDetails.class);
    }

    @Override
    public Project toProject(ProjectForm projectForm) {
        return modelMapper.map(projectForm, Project.class);
    }

    @Override
    public ProjectForm toProjectForm(Project project) {
        return modelMapper.map(project, ProjectForm.class);
    }
    
}
