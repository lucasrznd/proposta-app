package com.lucasrznd.propostaapp.controller;

import com.lucasrznd.propostaapp.dto.PropostaRequestDTO;
import com.lucasrznd.propostaapp.dto.PropostaResponseDTO;
import com.lucasrznd.propostaapp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> create(@RequestBody PropostaRequestDTO requestDTO) {
        PropostaResponseDTO response = propostaService.create(requestDTO);
        return ResponseEntity.ok(response);
    }

}
