package com.example.auth.domain.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth.domain.school.entities.Pedido_Oracao;

@Repository
public interface Pedido_OracaoRepository extends JpaRepository<Pedido_Oracao, Long> {

    Pedido_Oracao pedido = null;
    
}
