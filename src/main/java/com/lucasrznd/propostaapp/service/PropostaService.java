package com.lucasrznd.propostaapp.service;

import com.lucasrznd.propostaapp.dto.PropostaRequestDTO;
import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import com.lucasrznd.propostaapp.entities.Proposta;
import com.lucasrznd.propostaapp.mapper.PropostaMapper;
import com.lucasrznd.propostaapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    private NotificacaoService notificacaoService;

    private String exchange;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public List<PropostaResponseDTO> findAll() {
        return PropostaMapper.INSTANCE.toListDTO(propostaRepository.findAll());
    }

    public PropostaResponseDTO create(PropostaRequestDTO requestDTO) {
        Proposta proposta = PropostaMapper.INSTANCE.toEntity(requestDTO);
        propostaRepository.save(proposta);

        PropostaResponseDTO response = PropostaMapper.INSTANCE.toDTO(proposta);
        notificacaoService.notify(response, exchange);

        return response;
    }

}
