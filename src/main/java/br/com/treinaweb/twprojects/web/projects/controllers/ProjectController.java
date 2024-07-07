package br.com.treinaweb.twprojects.web.projects.controllers;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojects.core.exceptions.ProjectNotFoundException;
import br.com.treinaweb.twprojects.core.repositories.ClientRepository;
import br.com.treinaweb.twprojects.core.repositories.EmployeeRepository;
import br.com.treinaweb.twprojects.core.repositories.ProjectRepository;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectForm;
import br.com.treinaweb.twprojects.web.projects.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectMapper projectMapper;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @GetMapping
    public ModelAndView index() {
        var projects = projectRepository.findAll()
            .stream()
            .map(projectMapper::toProjectListItem)
            .toList();
        var model = Map.of("projects", projects);
        return new ModelAndView("projects/index", model);
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) {
        var project = projectRepository.findById(id)
            .map(projectMapper::toProjectDetails)
            .orElseThrow(ProjectNotFoundException::new);
        var model = Map.of("project", project);
        return new ModelAndView("projects/details", model);    
    }

    @GetMapping("/create")
    public ModelAndView create() {
        var clients = clientRepository.findAll();
        var team = employeeRepository.findTeam();
        var managers = employeeRepository.findManagers();
        
        var model = Map.of(
            "pageTitle", "Cadastro de Projeto",
            "projectForm", new ProjectForm(),
            "clients", clients,
            "team", team,
            "managers", managers
        );
        return new ModelAndView("projects/form", model);
    }

    @PostMapping("/create")
    public String create(ProjectForm projectForm) {
        var project = projectMapper.toProject(projectForm);
        projectRepository.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var projectForm = projectRepository.findById(id)
            .map(projectMapper::toProjectForm)
            .orElseThrow(ProjectNotFoundException::new);
        var clients = clientRepository.findAll();
        var team = employeeRepository.findTeam();
        var managers = employeeRepository.findManagers();
        
        var model = Map.of(
            "pageTitle", "Edição de Projeto",
            "projectForm", projectForm,
            "clients", clients,
            "team", team,
            "managers", managers
        );
        return new ModelAndView("projects/form", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ProjectForm projectForm) {
        var projectToUpdate = projectRepository.findById(id)
            .orElseThrow(ProjectNotFoundException::new);
        var projectData = projectMapper.toProject(projectForm);
        BeanUtils.copyProperties(projectData, projectToUpdate, "id");
        projectRepository.save(projectToUpdate);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        var project = projectRepository.findById(id)
            .orElseThrow(ProjectNotFoundException::new);
        projectRepository.delete(project);
        return "redirect:/projects";
    }
    
}
