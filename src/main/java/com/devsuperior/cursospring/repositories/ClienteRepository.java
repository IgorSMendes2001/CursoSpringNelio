package com.devsuperior.cursospring.repositories;

import com.devsuperior.cursospring.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    @Transactional(readOnly = true)
    public Cliente findByEmail(String email);
}
