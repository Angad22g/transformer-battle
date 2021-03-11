package com.aequilibrium.transformers.controller;

import java.util.List;

import javax.validation.Valid;

import com.aequilibrium.transformers.model.dto.TransformerDetailsDTO;
import com.aequilibrium.transformers.service.TransformerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/transformers")
public class TransformersController {


    private final TransformerOperationService transformerOperationService;

    @Autowired
    public TransformersController(TransformerOperationService transformersService) {
        this.transformerOperationService = transformersService;
    }

    @Operation(summary = "Create Transformers")
    @PostMapping
    public void createTransformer(
            @Valid @RequestBody(required = true) TransformerDetailsDTO transformerDto) {
        transformerOperationService.createTransformer(transformerDto);
    }

    @Operation(summary = "Update Transformers")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateTransformer(@RequestBody(required = true) TransformerDetailsDTO transformerDto) {
        transformerOperationService.updateTransformer(transformerDto);
    }

    @Operation(summary = "Delete Transformers by id")
    @DeleteMapping(value = "/{id}")
    public void deleteTransformer(@PathVariable(required = true) Long id) {
        transformerOperationService.deleteTransformer(id);
    }

    @Operation(summary = "Get Transformer list")
    @GetMapping
    public ResponseEntity<List<TransformerDetailsDTO>> getTransformers() {
        List<TransformerDetailsDTO> response = transformerOperationService.getTransformers();
        return !response.isEmpty() ? new ResponseEntity<>(transformerOperationService.getTransformers(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
