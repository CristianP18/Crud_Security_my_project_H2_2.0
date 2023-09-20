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

import com.example.auth.domain.school.entities.Aluno;
import com.example.auth.domain.school.repositories.AlunoRepository;




@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> alunos = repository.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/countAlunos/{cursoId}")
    public ResponseEntity<Long> countAlunosByCurso(@PathVariable Long cursoId) {
        Long count = repository.countAlunosByCursoId(cursoId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/alunos/{cursoId}")
    public ResponseEntity<List<String>> findAlunosCursoById(@PathVariable Long cursoId){
        List<String> alunosName = repository.findAlunosCursoById(cursoId);
        return ResponseEntity.ok(alunosName);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = repository.findById(id);

        if (optionalAluno.isPresent()) {
            return ResponseEntity.ok(optionalAluno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> insert(@Validated @RequestBody Aluno aluno) {
        Aluno result = repository.save(aluno);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @Validated @RequestBody Aluno updatedAluno) {
        Optional<Aluno> optionalAluno = repository.findById(id);

        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setName(updatedAluno.getName());
            aluno.setEmail(updatedAluno.getEmail());
            aluno.setCursos(updatedAluno.getCursos());
            Aluno updated = repository.save(aluno);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = repository.findById(id);

        if (optionalAluno.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
