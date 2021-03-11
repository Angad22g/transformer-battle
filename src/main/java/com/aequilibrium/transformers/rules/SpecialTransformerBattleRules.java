package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpecialTransformerBattleRules implements IBattleRule {

    private static final String OPTIMUS_PRIME = "Optimus Prime";
    private static final String PREDAKING = "Predaking";
    boolean isMatch;

    @Override
    public boolean isMatch(List<Transformers> transformers, TransformersBattleResponseDTO responseDTO) {
        Set<String> transformersSet = transformers.stream().map(Transformers::getName)
                .collect(Collectors.toSet());
        if (transformersSet.contains(OPTIMUS_PRIME) || transformersSet.contains(PREDAKING))
            isMatch = true;
        return isMatch;
    }

    public TransformersBattleResponseDTO execute(List<Transformers> transformers) {
        Set<String> transformersSet = transformers.stream().map(Transformers::getName)
                .collect(Collectors.toSet());

        if ((transformersSet.contains(OPTIMUS_PRIME) && transformersSet.contains(PREDAKING))
                || transformers.stream().filter(transformer -> transformer.getName().equalsIgnoreCase(OPTIMUS_PRIME))
                .count() > 1
                || transformers.stream().filter(transformer -> transformer.getName().equalsIgnoreCase(PREDAKING))
                .count() > 1) {
            return TransformersBattleResponseDTO.builder().winnerTeam("All Transformers are destroyed").build();
        } else if (transformersSet.contains(OPTIMUS_PRIME) && !transformersSet.contains(PREDAKING)) {
            return TransformersBattleResponseDTO.builder().winnerTeam(TransformerType.AUTOBOTS.type).build();
        } else if (transformersSet.contains(PREDAKING) && !transformersSet.contains(OPTIMUS_PRIME)) {
            return TransformersBattleResponseDTO.builder().winnerTeam(TransformerType.DECEPTICONS.type).build();
        }

        return null;
    }
}
