package com.lucasrznd.propostaapp.service;

import com.lucasrznd.propostaapp.dto.PropostaRequestDTO;
import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import com.lucasrznd.propostaapp.entities.Proposta;
import com.lucasrznd.propostaapp.mapper.PropostaMapper;
import com.lucasrznd.propostaapp.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public List<PropostaResponseDTO> findAll() {
        return PropostaMapper.INSTANCE.toListDTO(propostaRepository.findAll());
    }

    public PropostaResponseDTO create(PropostaRequestDTO requestDTO) {
        Proposta proposta = PropostaMapper.INSTANCE.toEntity(requestDTO);
        propostaRepository.save(proposta);

        return PropostaMapper.INSTANCE.toDTO(proposta);
    }

}