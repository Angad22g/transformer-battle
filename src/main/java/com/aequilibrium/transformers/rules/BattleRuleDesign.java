package com.aequilibrium.transformers.rules;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("allBattleRules")
public class BattleRuleDesign {

    public static List<IBattleRule> fetchAllRules(){
        List<IBattleRule> rules = new ArrayList<>();
        rules.add(new SpecialTransformerBattleRules());
        rules.add(new BasicTransformerBattleRules());
        return rules;
    }
}
