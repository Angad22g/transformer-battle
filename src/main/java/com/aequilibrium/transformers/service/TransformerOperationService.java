package com.aequilibrium.transformers.service;


import com.aequilibrium.transformers.model.dto.TransformerDetailsDTO;

import java.util.List;


public interface TransformerOperationService {

    void createTransformer(TransformerDetailsDTO transformerDetailsDTO);

    void updateTransformer(TransformerDetailsDTO transformerDetailsDTO);
    void deleteTransformer(Long id);
    List<TransformerDetailsDTO> getTransformers();

}
