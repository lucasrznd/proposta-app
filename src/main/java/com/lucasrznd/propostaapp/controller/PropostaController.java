package com.lucasrznd.propostaapp.controller;

import com.lucasrznd.propostaapp.dto.PropostaRequestDTO;
import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> create(@RequestBody PropostaRequestDTO requestDTO) {
        return null;
    }

}
