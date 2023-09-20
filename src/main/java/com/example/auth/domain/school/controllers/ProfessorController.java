package com.example.auth.domain.school.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.domain.school.entities.Professor;
import com.example.auth.domain.school.repositories.ProfessorRepository;


@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public List<Professor> getAllProfessors() {
        List<Professor> professors = repository.findAll();
        return professors;
    }

    @GetMapping("/countProfessores/{cursoId}")
    public ResponseEntity<Long> countProfessoresById(@PathVariable Long cursoId){
        Long count = repository.countProfessoresByCursoId(cursoId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/professores/{cursoId}")
    public ResponseEntity<List<String>> findProfessoresByCursoId(@PathVariable Long cursoId){
       List<String> professorNames = repository.findProfessorNamesByCursoId(cursoId);
       return ResponseEntity.ok(professorNames);
}


    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Optional<Professor> optionalProfessor = repository.findById(id);
        return optionalProfessor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@Validated @RequestBody Professor professor) {
        Professor savedProfessor = repository.save(professor);
        return ResponseEntity.ok(savedProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @Validated @RequestBody Professor updatedProfessor) {
        Optional<Professor> optionalProfessor = repository.findById(id);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setName(updatedProfessor.getName());
            professor.setEmail(updatedProfessor.getEmail());
            professor.setCursos(updatedProfessor.getCursos());
            Professor updated = repository.save(professor);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        Optional<Professor> optionalProfessor = repository.findById(id);

        if (optionalProfessor.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
