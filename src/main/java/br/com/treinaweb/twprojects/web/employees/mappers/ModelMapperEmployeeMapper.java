package br.com.treinaweb.twprojects.web.employees.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Employee;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeDetails;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeForm;
import br.com.treinaweb.twprojects.web.employees.dtos.EmployeeListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "modelmapper")
public class ModelMapperEmployeeMapper implements EmployeeMapper {

    private final ModelMapper modelMapper;

    @Override
    public Employee toEmployee(EmployeeForm employeeForm) {
        return modelMapper.map(employeeForm, Employee.class);
    }

    @Override
    public EmployeeForm toEmployeeForm(Employee employee) {
        return modelMapper.map(employee, EmployeeForm.class);
    }

    @Override
    public EmployeeListItem toEmployeeListItem(Employee employee) {
        return modelMapper.map(employee, EmployeeListItem.class);
    }

    @Override
    public EmployeeDetails toEmployeeDetails(Employee employee) {
        return modelMapper.map(employee, EmployeeDetails.class);
    }
    
}
