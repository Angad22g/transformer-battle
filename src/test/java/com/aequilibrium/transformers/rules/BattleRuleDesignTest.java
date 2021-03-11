package com.aequilibrium.transformers.rules;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class BattleRuleDesignTest {


    private BattleRuleDesign battleRuleDesign;

    @Before
    public void setup(){
        battleRuleDesign = new BattleRuleDesign();
    }
    @Test
    public void should_show_two_rules(){
        assertEquals(2,BattleRuleDesign.fetchAllRules().size());
    }

    @Test
    public void should_show_two_rules_with_proper_order(){
        assertEquals(2,BattleRuleDesign.fetchAllRules().size());
        assertTrue(BattleRuleDesign.fetchAllRules().get(0) instanceof SpecialTransformerBattleRules);
        assertTrue(BattleRuleDesign.fetchAllRules().get(1) instanceof BasicTransformerBattleRules);
    }
}
