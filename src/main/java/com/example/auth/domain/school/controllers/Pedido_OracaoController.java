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

import com.example.auth.domain.school.entities.Pedido_Oracao;
import com.example.auth.domain.school.repositories.Pedido_OracaoRepository;


@RestController
@RequestMapping(value = "/pedido_oracao")
public class Pedido_OracaoController {

    @Autowired
    private Pedido_OracaoRepository repository;

    @GetMapping
    public ResponseEntity<List<Pedido_Oracao>> findAll() {
        List<Pedido_Oracao> pedido = repository.findAll();
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido_Oracao> findById(@PathVariable Long id) {
        Optional<Pedido_Oracao> optionalPedido = repository.findById(id);

        if (optionalPedido.isPresent()) {
            return ResponseEntity.ok(optionalPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@PostMapping
public ResponseEntity<Pedido_Oracao> insert(@Validated @RequestBody Pedido_Oracao pedido) {
    Pedido_Oracao result = repository.save(pedido);
    return ResponseEntity.ok(result);
}

@PutMapping("/{id}")
public ResponseEntity<Pedido_Oracao> updatePedido(@PathVariable Long id, @Validated @RequestBody Pedido_Oracao updatedPedido) {
    Optional<Pedido_Oracao> optionalPedido = repository.findById(id);

    if (optionalPedido.isPresent()) {
        Pedido_Oracao pedido = optionalPedido.get();
        pedido.setName(updatedPedido.getName());
        pedido.setPedido_oracao(updatedPedido.getPedido_oracao());
        Pedido_Oracao updated = repository.save(pedido);
        return ResponseEntity.ok(updated);
    } else {
        return ResponseEntity.notFound().build();
    }
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
    Optional<Pedido_Oracao> optionalPedido = repository.findById(id);

    if (optionalPedido.isPresent()) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}