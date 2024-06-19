package com.lucasrznd.propostaapp.repository;

import com.lucasrznd.propostaapp.entities.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
