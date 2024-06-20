package com.lucasrznd.propostaapp.service;

import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public void notify(PropostaResponseDTO proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
