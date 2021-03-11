package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

import java.util.List;

@FunctionalInterface
public interface IBattleRuleExecutor {

    public TransformersBattleResponseDTO executeAll(List<IBattleRule> battleRules, List<Transformers> transformers);
}
