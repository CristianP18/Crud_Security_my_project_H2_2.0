package com.example.auth.domain.school.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.auth.domain.school.entities.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    
    @Query("SELECT COUNT(p) FROM Professor p JOIN p.cursos c WHERE c.id = :cursoId")
    Long countProfessoresByCursoId(@Param("cursoId") Long cursoId);

    @Query("SELECT p.name FROM Professor p JOIN p.cursos c WHERE c.id = :cursoId")
    List<String> findProfessorNamesByCursoId(@Param("cursoId") Long cursoId);
    
}
