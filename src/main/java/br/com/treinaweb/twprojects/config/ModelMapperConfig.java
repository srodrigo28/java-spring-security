package br.com.treinaweb.twprojects.config;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.treinaweb.twprojects.core.models.Address;
import br.com.treinaweb.twprojects.core.models.Client;
import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.core.models.Project;
import br.com.treinaweb.twprojects.core.utils.StringUtils;
import br.com.treinaweb.twprojects.web.clients.dtos.ClientForm;
import br.com.treinaweb.twprojects.web.employees.dtos.AddressForm;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeDetails;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeForm;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeListItem;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectDetails;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectForm;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectListItem;
import br.com.treinaweb.twprojects.web.projects.dtos.ProjectTeamListItem;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ClientForm.class, Client.class)
            .addMappings(mapper -> mapper
                .skip(Client::setId)
            )
            .addMappings(mapper -> mapper
                .using(toCleanedPhone())
                .map(ClientForm::getPhone, Client::setPhone)
        );

        modelMapper.createTypeMap(Client.class, ClientForm.class)
            .addMappings(mapper -> mapper
                .using(toFormattedPhone())
                .map(Client::getPhone, ClientForm::setPhone)
        );

        modelMapper.createTypeMap(AddressForm.class, Address.class)
            .addMappings(mapper -> mapper
                .using(toCleanedZipCode())
                .map(AddressForm::getZipCode, Address::setZipCode)
        );

        modelMapper.createTypeMap(Address.class, AddressForm.class)
            .addMappings(mapper -> mapper
                .using(toFormattedZipCode())
                .map(Address::getZipCode, AddressForm::setZipCode)
            );

        modelMapper.createTypeMap(EmployeeForm.class, Employee.class)
            .addMappings(mapper -> mapper
                .skip(Employee::setId)
            )
            .addMappings(mapper -> mapper
                .using(toCleanedCpf())
                .map(EmployeeForm::getCpf, Employee::setCpf)    
            )
            .addMappings(mapper -> mapper
                .using(toCleanedPhone())
                .map(EmployeeForm::getPhone, Employee::setPhone)
            );
            
        modelMapper.createTypeMap(Employee.class, EmployeeForm.class)
            .addMappings(mapper -> mapper
                .using(toFormattedCpf())
                .map(Employee::getCpf, EmployeeForm::setCpf)
            )
            .addMappings(mapper -> mapper
                .using(toFormattedPhone())
                .map(Employee::getPhone, EmployeeForm::setPhone)
            );

        modelMapper.createTypeMap(Employee.class, EmployeeListItem.class)
            .addMappings(mapper -> mapper
                .map(src -> src.getPosition().getName(), EmployeeListItem::setPosition)
            )
            .addMappings(mapper -> mapper
                .using(toFormattedPhone())
                .map(Employee::getPhone, EmployeeListItem::setPhone)
            );

        modelMapper.createTypeMap(Employee.class, EmployeeDetails.class)
            .addMappings(mapper -> mapper
                .using(toFormattedCpf())
                .map(Employee::getCpf, EmployeeDetails::setCpf)
            )
            .addMappings(mapper -> mapper
                .using(toFormattedPhone())
                .map(Employee::getPhone, EmployeeDetails::setPhone)
            )
            .addMappings(mapper -> mapper
                .map(src -> src.getPosition().getName(), EmployeeDetails::setPosition)
            )
            .addMappings(mapper -> mapper
                .using(toFormattedAddress())
                .map(Employee::getAddress, EmployeeDetails::setAddress)
            );

        modelMapper.createTypeMap(Project.class, ProjectListItem.class)
            .addMappings(mapper -> mapper
                .map(src -> src.getClient().getName(), ProjectListItem::setClient)
            )
            .addMappings(mapper -> mapper
                .map(src -> src.getManager().getName(), ProjectListItem::setManager)
            );

        modelMapper.createTypeMap(ProjectForm.class, Project.class)
            .addMappings(mapper -> mapper
                .skip(Project::setId)
            )
            .addMappings(mapper -> mapper
                .using(toClient())
                .map(ProjectForm::getClient, Project::setClient)
            )
            .addMappings(mapper -> mapper
                .using(toEmployee())
                .map(ProjectForm::getManager, Project::setManager)
            )
            .addMappings(mapper -> mapper
                .using(toEmployees())
                .map(ProjectForm::getTeam, Project::setTeam)
            );

        modelMapper.createTypeMap(Project.class, ProjectForm.class)
            .addMappings(mapper -> mapper
                .map(src -> src.getClient().getId(), ProjectForm::setClient)
            )
            .addMappings(mapper -> mapper
                .map(src -> src.getManager().getId(), ProjectForm::setManager)
            )
            .addMappings(mapper -> mapper
                .using(toEmployeeIds())
                .map(Project::getTeam, ProjectForm::setTeam)
            );

        modelMapper.createTypeMap(Project.class, ProjectDetails.class)
            .addMappings(mapper -> mapper
                .map(src -> src.getClient().getName(), ProjectDetails::setClient)
            )
            .addMappings(mapper -> mapper
                .map(src -> src.getManager().getName(), ProjectDetails::setManager)
            );

        modelMapper.createTypeMap(Employee.class, ProjectTeamListItem.class)
            .addMappings(mapper -> mapper
                .map(src -> src.getPosition().getName(), ProjectTeamListItem::setPosition)
            );

        return modelMapper;
    }

    private Converter<String, String> toCleanedPhone() {
        return context -> StringUtils.cleanPhone(context.getSource());
    }

    private Converter<String, String> toFormattedPhone() {
        return context -> StringUtils.formatPhone(context.getSource());
    }

    private Converter<String, String> toCleanedZipCode() {
        return context -> StringUtils.cleanZipCode(context.getSource());
    }

    private Converter<String, String> toFormattedZipCode() {
        return context -> StringUtils.formatZipCode(context.getSource());
    }

    private Converter<String, String> toCleanedCpf() {
        return context -> StringUtils.cleanCpf(context.getSource());
    }

    private Converter<String, String> toFormattedCpf() {
        return context -> StringUtils.formatCpf(context.getSource());
    }

    private Converter<Address, String> toFormattedAddress() {
        return context -> String.format(
            "%s, %s - %s - %s - %s",
            context.getSource().getStreet(),
            context.getSource().getNumber(),
            context.getSource().getNeighborhood(),
            context.getSource().getCity(),
            context.getSource().getState()
        );
    }

    private Converter<Long, Client> toClient() {
        return context -> Client.builder()
            .id(context.getSource())
            .build();
    }

    private Converter<Long, Employee> toEmployee() {
        return context -> Employee.builder()
            .id(context.getSource())
            .build();
    }

    private Converter<List<Long>, List<Employee>> toEmployees() {
        return context -> context.getSource()
            .stream()
            .map((id) -> Employee.builder().id(id).build())
            .collect(Collectors.toList());
    }

    private Converter<List<Employee>, List<Long>> toEmployeeIds() {
        return context -> context.getSource()
            .stream()
            .map(Employee::getId)
            .toList();
    }
}
