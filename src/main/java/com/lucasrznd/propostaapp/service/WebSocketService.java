package com.lucasrznd.propostaapp.service;

import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private SimpMessagingTemplate template;

    public WebSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void notify(PropostaResponseDTO propostaResponseDTO) {
        template.convertAndSend("/propostas", propostaResponseDTO);
    }

}
