package br.com.treinaweb.twprojects.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.treinaweb.twprojects.core.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = "position")
    List<Employee> findAll();

    @EntityGraph(attributePaths = { "position", "address", "projects" })
    Optional<Employee> findById(Long id);

    List<Employee> findByPositionName(String positionName);

    List<Employee> findByPositionNameNot(String positionName);

    @Query("select e from Employee e where e.position.name = 'Gerente de projetos'")
    List<Employee> findManagers();

    @Query("select e from Employee e where e.position.name <> 'Gerente de projetos'")
    List<Employee> findTeam();

    Optional<Employee> findByEmail(String email);
    
}
