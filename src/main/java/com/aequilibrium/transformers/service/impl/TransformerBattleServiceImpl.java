package com.aequilibrium.transformers.service.impl;

import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.aequilibrium.transformers.repository.TransformersRepository;
import com.aequilibrium.transformers.rules.BattleRuleDesign;
import com.aequilibrium.transformers.rules.IBattleRuleExecutor;
import com.aequilibrium.transformers.service.TransformerBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformerBattleServiceImpl implements TransformerBattleService {

    private TransformersRepository transformersRepository;
    private IBattleRuleExecutor iBattleRuleExecutor;

    @Autowired
    public  TransformerBattleServiceImpl(TransformersRepository transformersRepository,
                                         IBattleRuleExecutor iBattleRuleExecutor){
        this.transformersRepository= transformersRepository;
        this.iBattleRuleExecutor = iBattleRuleExecutor;
    }

    @Override
    public TransformersBattleResponseDTO battleWinners(List<Long> transformersIds){
        TransformersBattleResponseDTO transformersBattleResponseDTO = null;
        Iterable<Transformers> transformersItr = transformersRepository.findAllById(transformersIds);
        List<Transformers> transformers = new ArrayList<>();
        transformersItr.forEach(transformers::add);
        transformersBattleResponseDTO = iBattleRuleExecutor.executeAll(BattleRuleDesign.fetchAllRules(),transformers);
        return transformersBattleResponseDTO;
    }

}
