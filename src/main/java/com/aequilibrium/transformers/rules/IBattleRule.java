package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

import java.util.List;

public interface IBattleRule {
    public boolean isMatch(List<Transformers> transformers,TransformersBattleResponseDTO responseDTO);
    public TransformersBattleResponseDTO execute(List<Transformers> transformers);
}
