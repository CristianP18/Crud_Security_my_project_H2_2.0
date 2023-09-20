package com.example.auth.domain.school.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.auth.domain.school.entities.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT COUNT(a) FROM Aluno a JOIN a.cursos c WHERE c.id = :cursoId")
    Long countAlunosByCursoId(@Param("cursoId") Long cursoId);

    @Query("SELECT a.name FROM Aluno a JOIN a.cursos c WHERE c.id = :cursoId")
    List<String> findAlunosCursoById(@Param("cursoId") Long cursoId);

}
