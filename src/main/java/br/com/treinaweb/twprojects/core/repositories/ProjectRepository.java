package br.com.treinaweb.twprojects.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twprojects.core.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @EntityGraph(attributePaths = { "client", "manager" })
    List<Project> findAll();

    @EntityGraph(attributePaths = { "client", "manager", "team", "team.position" })
    Optional<Project> findById(Long id);
    
}
