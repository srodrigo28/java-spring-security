package br.com.treinaweb.twprojects.web.projects.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.exceptions.ClientNotFoundException;
import br.com.treinaweb.twprojects.core.exceptions.EmployeeNotFoundException;
import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.core.repositories.ClientRepository;
import br.com.treinaweb.twprojects.core.repositories.EmployeeRepository;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectDetails;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectForm;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class ProjectMapperImpl implements ProjectMapper {

    private final ClientRepository clientRepository;
    private final ProjectTeamMapper projectTeamMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public ProjectListItem toProjectListItem(Project project) {
        return ProjectListItem.builder()
            .id(project.getId())
            .name(project.getName())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .client(project.getClient().getName())
            .manager(project.getManager().getName())
            .budget(project.getBudget())
            .build();
    }

    @Override
    public ProjectDetails toProjectDetails(Project project) {
        var team = project.getTeam()
            .stream()
            .map(projectTeamMapper::toProjectTeamListItem)
            .toList();

        return ProjectDetails.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .budget(project.getBudget())
            .cost(project.getCost())
            .client(project.getClient().getName())
            .manager(project.getManager().getName())
            .team(team)
            .build();
    }

    @Override
    public Project toProject(ProjectForm projectForm) {
        var client = clientRepository.findById(projectForm.getClient())
            .orElseThrow(ClientNotFoundException::new);

        var manager = employeeRepository.findById(projectForm.getManager())
            .orElseThrow(EmployeeNotFoundException::new);

        var team = employeeRepository.findAllById(projectForm.getTeam());

        return Project.builder()
            .name(projectForm.getName())
            .description(projectForm.getDescription())
            .startDate(projectForm.getStartDate())
            .endDate(projectForm.getEndDate())
            .budget(projectForm.getBudget())
            .cost(projectForm.getCost())
            .client(client)
            .manager(manager)
            .team(team)
            .build();
    }

    @Override
    public ProjectForm toProjectForm(Project project) {
        var team = project.getTeam()
            .stream()
            .map(Employee::getId)
            .toList();

        return ProjectForm.builder()
            .name(project.getName())
            .description(project.getDescription())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .budget(project.getBudget())
            .cost(project.getCost())
            .client(project.getClient().getId())
            .manager(project.getManager().getId())
            .team(team)
            .build();
    }
    
}
