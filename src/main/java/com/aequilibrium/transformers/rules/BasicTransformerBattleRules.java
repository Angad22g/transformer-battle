package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BasicTransformerBattleRules implements IBattleRule {
    private Integer numberOfBattles;
    private Integer autobotsWins;
    private Integer decepticonsWins;
    private static final String OPTIMUS_PRIME = "Optimus Prime";
    private static final String PREDAKING = "Predaking";

    @Override
    public boolean isMatch(List<Transformers> transformers, TransformersBattleResponseDTO responseDTO) {
        return true;
    }

    @Override
    public TransformersBattleResponseDTO execute(List<Transformers> transformers) {
        List<Transformers> autobots = this.sliptTransformerTypeByType(transformers, TransformerType.AUTOBOTS);
        List<Transformers> decepticons = this.sliptTransformerTypeByType(transformers, TransformerType.DECEPTICONS);

        this.sortTransformerTypeByRank(autobots);
        this.sortTransformerTypeByRank(decepticons);

        List<Transformers> nonFightList = this.removeFighters(autobots, decepticons);

        numberOfBattles = 0;
        autobotsWins = 0;
        decepticonsWins = 0;

        for (int i = 0; i < decepticons.size(); i++) {
            for (int j = 0; j < autobots.size(); j++) {
                    this.oneOnOneFight(autobots.get(i), decepticons.get(i));
                i++;
            }
        }

        String winnerTransformerType = this.getWinningTransformerType();

        return TransformersBattleResponseDTO.builder().numberOfBattles(numberOfBattles).winnerTeam(winnerTransformerType)
                .survivorsLosingTeam(
                        nonFightList.isEmpty() ? null : this.getLosingTransformerTypeSurvivors(nonFightList, winnerTransformerType))
                .build();
    }

    private void oneOnOneFight(Transformers autobot, Transformers decepticon) {
        if (this.isLeader(autobot, decepticon)) {
            numberOfBattles++;
        } else if (this.hasRunAwayOpponent(autobot, decepticon)) {
            numberOfBattles++;
        } else if (this.hasSkillWinner(autobot, decepticon)) {
            numberOfBattles++;
        } else {
            this.getOverallRateWinner(autobot, decepticon);
            numberOfBattles++;
        }
    }

    private boolean hasRunAwayOpponent(Transformers autobot, Transformers decepticon) {
        if (autobot.getCourage() - decepticon.getCourage() >= 4
                || autobot.getStrength() - decepticon.getStrength() >= 3) {
            autobotsWins++;
            return true;
        } else if (decepticon.getCourage() - autobot.getCourage() >= 4
                || decepticon.getStrength() - autobot.getStrength() >= 3) {
            decepticonsWins++;
            return true;
        }
        return false;
    }

    private boolean isLeader(Transformers autobot, Transformers decepticon) {
        if (OPTIMUS_PRIME.equals(autobot.getName()) && !PREDAKING.equals(decepticon.getName())) {
            autobotsWins++;
            return true;
        } else if (!OPTIMUS_PRIME.equals(autobot.getName()) && PREDAKING.equals(decepticon.getName())) {
            decepticonsWins++;
            return true;
        }
        return false;
    }

    private boolean hasSkillWinner(Transformers autobot, Transformers decepticon) {
        if (autobot.getSkill() - decepticon.getSkill() >= 3) {
            autobotsWins++;
            return true;
        } else if (decepticon.getSkill() - autobot.getSkill() >= 3) {
            decepticonsWins++;
            return true;
        }
        return false;
    }

    private void getOverallRateWinner(Transformers autobot, Transformers decepticon) {
        Integer autoBotOverallRate = this.getOverallRating(autobot);
        Integer decepticonOverrallRate = this.getOverallRating(decepticon);

        if (autoBotOverallRate > decepticonOverrallRate) {
            autobotsWins++;
        } else if (autoBotOverallRate < decepticonOverrallRate) {
            decepticonsWins++;
        } else {
            autobotsWins++;
            decepticonsWins++;
        }

    }

    private String getWinningTransformerType() {
        if (autobotsWins > decepticonsWins) {
            return TransformerType.AUTOBOTS.type;
        } else if (autobotsWins < decepticonsWins) {
            return TransformerType.DECEPTICONS.type;
        }
        return "Tie";
    }

    private List<Transformers> sliptTransformerTypeByType(List<Transformers> transformers, TransformerType TransformerType) {
        return transformers.stream().filter(transformer -> transformer.getTeam().equalsIgnoreCase(TransformerType.type))
                .collect(Collectors.toList());
    }

    private void sortTransformerTypeByRank(List<Transformers> transformers) {
        transformers.sort(Comparator.comparingInt(this::getTransformerRank).reversed());
    }

    private Integer getTransformerRank(Transformers transfomer) {
        return transfomer.getRank();
    }

    private Integer getOverallRating(Transformers transformer) {
        return transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed()
                + transformer.getEndurance() + transformer.getFirepower();
    }

    private List<Transformers> removeFighters(List<Transformers> autobots,
                                              List<Transformers> decepticons) {
        List<Transformers> nonFightList = new ArrayList<>();

        if (autobots.size() > decepticons.size()) {
            nonFightList.add(autobots.remove(autobots.size() - 1));
        } else if (autobots.size() < decepticons.size()) {
            nonFightList.add(decepticons.remove(decepticons.size() - 1));
        }
        return nonFightList;
    }

    private List<String> getLosingTransformerTypeSurvivors(List<Transformers> nonFightList, String winnerTransformerType) {
        List<String> losingTransformerTypeSurvivors = new ArrayList<>();

        if (!winnerTransformerType.equalsIgnoreCase("Tie")) {
            nonFightList.stream()
                    .filter(transformer -> TransformerType.getByType(transformer.getTeam()) != (TransformerType.getByType(winnerTransformerType)))
                    .forEach(transformer -> losingTransformerTypeSurvivors.add(transformer.getName()));
        }

        return losingTransformerTypeSurvivors;

    }
}
