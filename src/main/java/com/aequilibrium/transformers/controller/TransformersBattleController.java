package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.aequilibrium.transformers.service.TransformerBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aequilibrium.transformers.model.dto.BattleRequestDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/battle")
public class TransformersBattleController {

    private final TransformerBattleService transformersBattleService;

    @Autowired
    public TransformersBattleController(TransformerBattleService transformersBattleService) {
        this.transformersBattleService = transformersBattleService;
    }

    @Operation(summary = "Starts Transformers battle by Transformers id list")
    @PostMapping
    public ResponseEntity<TransformersBattleResponseDTO> transformersBattle(
            @RequestBody(required = true) BattleRequestDTO battleRequestDTO) {
        TransformersBattleResponseDTO response = transformersBattleService.battleWinners(battleRequestDTO.getTransformersIds());
        return response != null ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
