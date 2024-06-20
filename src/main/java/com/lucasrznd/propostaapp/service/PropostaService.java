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

    private NotificacaoRabbitService notificacaoRabbitService;

    private String exchange;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    public List<PropostaResponseDTO> findAll() {
        return PropostaMapper.INSTANCE.toListDTO(propostaRepository.findAll());
    }

    public PropostaResponseDTO create(PropostaRequestDTO requestDTO) {
        Proposta proposta = PropostaMapper.INSTANCE.toEntity(requestDTO);
        propostaRepository.save(proposta);

        notifyRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.toDTO(proposta);
    }

    private void notifyRabbitMQ(Proposta proposta) {
        try {
            notificacaoRabbitService.notify(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

}
