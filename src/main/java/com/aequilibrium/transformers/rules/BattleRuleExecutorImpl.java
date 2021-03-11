package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleRuleExecutorImpl implements IBattleRuleExecutor {
    TransformersBattleResponseDTO responseDTO=null;
    @Override
    public TransformersBattleResponseDTO executeAll(List<IBattleRule> battleRules, List<Transformers> transformers){
        battleRules.forEach(x->{
            if(x.isMatch(transformers,responseDTO))
                responseDTO = x.execute(transformers);
        });
        return responseDTO;
    }

}
