package com.aequilibrium.transformers.service.impl;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformerDetailsDTO;
import com.aequilibrium.transformers.repository.TransformersRepository;
import com.aequilibrium.transformers.service.TransformerOperationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransformerOperationServiceImpl implements TransformerOperationService {

    private final TransformersRepository transformersRepository;

    @Autowired
    public TransformerOperationServiceImpl(TransformersRepository transformersRepository) {
        this.transformersRepository = transformersRepository;
    }

    public void createTransformer(TransformerDetailsDTO transformer){
        log.debug("Create a transformer with id:- {}", transformer.getId());
        this.validate(transformer.getTeam());
        transformersRepository.save(this.convertToEntity(transformer));
    };

    public void updateTransformer(TransformerDetailsDTO transformer){
        log.debug("Update transformer with id:-{}", transformer.getId());
        transformersRepository.save(this.convertToEntity(transformer));
    };

    public void deleteTransformer(Long id){
        log.debug("Delete transformer with id:-{}", id);
        transformersRepository.deleteById(id);
    };

    public List<TransformerDetailsDTO> getTransformers(){
        log.info("Fetching list of all transformers");
        List<TransformerDetailsDTO> transformerList = new ArrayList<>();
        Iterable<Transformers> fetchedTransformers = transformersRepository.findAll();
        fetchedTransformers.forEach(x->
        {
            transformerList.add(convertToDto(x));
        });
        return transformerList;
    };


    private TransformerDetailsDTO convertToDto(Transformers entity) {
        return new ObjectMapper().convertValue(entity, TransformerDetailsDTO.class);
    }

    private Transformers convertToEntity(TransformerDetailsDTO dto) {
        return new ObjectMapper().convertValue(dto, Transformers.class);
    }

    private void validate(String team) {
        TransformerType.getByType(team);
    }

}
