package br.com.treinaweb.twprojects.web.employees.mappers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.exceptions.PositionNotFoundException;
import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.core.repositories.PositionRepository;
import br.com.treinaweb.twprojects.core.utils.StringUtils;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeDetails;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeForm;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "local")
public class EmployeeMapperImpl implements EmployeeMapper {

    private final AddressMapper addressMapper;
    private final PositionRepository positionRepository;
    private final EmployeeProjectMapper employeeProjectMapper;

    @Override
    public Employee toEmployee(EmployeeForm employeeForm) {
        var position = positionRepository.findById(employeeForm.getPositionId())
            .orElseThrow(PositionNotFoundException::new);

        return Employee.builder()
            .name(employeeForm.getName())
            .email(employeeForm.getEmail())
            .phone(StringUtils.cleanPhone(employeeForm.getPhone()))
            .cpf(StringUtils.cleanCpf(employeeForm.getCpf()))
            .birthDate(employeeForm.getBirthDate())
            .hireDate(employeeForm.getHireDate())
            .resignationDate(employeeForm.getResignationDate())
            .address(addressMapper.toAddress(employeeForm.getAddress()))
            .position(position)
            .build();
    }

    @Override
    public EmployeeForm toEmployeeForm(Employee employee) {
        return EmployeeForm.builder()
            .name(employee.getName())
            .email(employee.getEmail())
            .phone(StringUtils.formatPhone(employee.getPhone()))
            .cpf(StringUtils.formatCpf(employee.getCpf()))
            .birthDate(employee.getBirthDate())
            .hireDate(employee.getHireDate())
            .resignationDate(employee.getResignationDate())
            .address(addressMapper.toAddressForm(employee.getAddress()))
            .positionId(employee.getPosition().getId())
            .build();
    }

    @Override
    public EmployeeListItem toEmployeeListItem(Employee employee) {
        return EmployeeListItem.builder()
            .id(employee.getId())
            .name(employee.getName())
            .email(employee.getEmail())
            .phone(StringUtils.formatPhone(employee.getPhone()))
            .position(employee.getPosition().getName())
            .build();
    }

    @Override
    public EmployeeDetails toEmployeeDetails(Employee employee) {
        var projects = employee.getProjects()
            .stream()
            .map(employeeProjectMapper::toEmployeeProjectListItem)
            .toList();

        return EmployeeDetails.builder()
            .name(employee.getName())
            .email(employee.getEmail())
            .phone(StringUtils.formatPhone(employee.getPhone()))
            .cpf(StringUtils.formatCpf(employee.getCpf()))
            .birthDate(employee.getBirthDate())
            .hireDate(employee.getHireDate())
            .resignationDate(employee.getResignationDate())
            .address(addressMapper.formatAddress(employee.getAddress()))
            .position(employee.getPosition().getName())
            .projects(projects)
            .build();
    }
    
}
