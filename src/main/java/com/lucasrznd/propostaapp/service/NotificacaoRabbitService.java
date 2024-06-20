package com.lucasrznd.propostaapp.service;

import com.lucasrznd.propostaapp.entities.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

    private RabbitTemplate rabbitTemplate;

    public void notify(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
