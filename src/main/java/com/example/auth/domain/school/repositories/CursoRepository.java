package com.example.auth.domain.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth.domain.school.entities.Curso;



@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
   
}
