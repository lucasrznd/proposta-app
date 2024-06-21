package com.lucasrznd.propostaapp.listener;

import com.lucasrznd.propostaapp.entities.Proposta;
import com.lucasrznd.propostaapp.mapper.PropostaMapper;
import com.lucasrznd.propostaapp.repository.PropostaRepository;
import com.lucasrznd.propostaapp.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private PropostaRepository propostaRepository;

    private WebSocketService webSocketService;

    public PropostaConcluidaListener(PropostaRepository propostaRepository, WebSocketService webSocketService) {
        this.propostaRepository = propostaRepository;
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        updateProposta(proposta);

        webSocketService.notify(PropostaMapper.INSTANCE.toDTO(proposta));
    }

    private void updateProposta(Proposta proposta) {
        propostaRepository.updateProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
    }

}
