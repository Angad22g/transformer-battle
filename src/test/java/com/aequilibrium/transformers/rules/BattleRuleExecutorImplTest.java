package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BattleRuleExecutorImplTest {

    @InjectMocks
    private BattleRuleExecutorImpl battleRuleExecutor;

    private IBattleRule battleRule;

    @Mock
    private BasicTransformerBattleRules basicTransformerBattleRules;

    @Mock
    private SpecialTransformerBattleRules specialTransformerBattleRules;

    private static Transformers AUTOBOT_FIRST;
    private static Transformers DECEPTICON_FIRST;
    private List<IBattleRule> battleRules;
    private List<Transformers> transformers;
    @Before
    public void setup(){
        AUTOBOT_FIRST = Transformers.builder().id(3L).name("1 Autobot").team(TransformerType.AUTOBOTS.type).build();
        DECEPTICON_FIRST = Transformers.builder().id(2L).name("1 Decepticon").team(TransformerType.DECEPTICONS.type).build();

        battleRules = Arrays.asList(basicTransformerBattleRules,specialTransformerBattleRules );
        transformers = Arrays.asList(AUTOBOT_FIRST,DECEPTICON_FIRST);
    }

    @Test
    public void verify_if_rules_are_invoked(){
        battleRuleExecutor.executeAll(battleRules,transformers);
        verify(basicTransformerBattleRules, times(1)).isMatch(any(),any());
        verify(specialTransformerBattleRules, times(1)).isMatch(any(),any());
    }

}
