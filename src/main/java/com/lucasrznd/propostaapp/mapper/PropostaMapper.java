package com.lucasrznd.propostaapp.mapper;

import com.lucasrznd.propostaapp.dto.PropostaRequestDTO;
import com.lucasrznd.propostaapp.entities.Proposta;
import org.mapstruct.Mapper;

@Mapper
public interface PropostaMapper {

    Proposta toEntity(PropostaRequestDTO requestDTO);

}
