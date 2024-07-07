package br.com.treinaweb.twprojects.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twprojects.core.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
    
}
