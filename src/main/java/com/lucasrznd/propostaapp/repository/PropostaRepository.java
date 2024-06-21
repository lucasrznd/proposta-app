package com.lucasrznd.propostaapp.repository;

import com.lucasrznd.propostaapp.entities.Proposta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    List<Proposta> findAllByIntegradaIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id")
    void updateProposta(Long id, boolean aprovada, String observacao);

}
