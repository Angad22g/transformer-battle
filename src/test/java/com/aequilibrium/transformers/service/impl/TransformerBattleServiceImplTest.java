package com.aequilibrium.transformers.service.impl;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.aequilibrium.transformers.repository.TransformersRepository;
import com.aequilibrium.transformers.rules.IBattleRuleExecutor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransformerBattleServiceImplTest {

    @InjectMocks
    private TransformerBattleServiceImpl transformerBattleService;

    @Mock
    private TransformersRepository transformersRepository;

    @Mock
    private IBattleRuleExecutor iBattleRuleExecutor;



    private static Transformers OPTIMUS_PRIME;
    private static Transformers PREDAKING;
    private static Transformers AUTOBOT_FIRST;
    private static Transformers DECEPTICON_FIRST;

    @Before
    public void setup(){
        OPTIMUS_PRIME = Transformers.builder().id(1L).name("Optimus Prime").team(TransformerType.AUTOBOTS.type).build();
        PREDAKING = Transformers.builder().id(2L).name("Predaking").team(TransformerType.DECEPTICONS.type).build();
        AUTOBOT_FIRST = Transformers.builder().id(3L).name("1 Autobot").team(TransformerType.AUTOBOTS.type).skill(9).courage(4).strength(3).build();
        DECEPTICON_FIRST = Transformers.builder().id(2L).name("1 Decepticon").team(TransformerType.DECEPTICONS.type).skill(7).courage(4).strength(3).build();
        when(transformersRepository.findAllById(Arrays.asList(2L,3L))).thenReturn(Arrays.asList(AUTOBOT_FIRST, DECEPTICON_FIRST));

    }

    @Test
    public void should_return_autobots_if_have_more_skill(){
        when(iBattleRuleExecutor.executeAll(anyList(),anyList())).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam("A").build());
        TransformersBattleResponseDTO result = transformerBattleService.battleWinners(Arrays.asList(2L,3L));

        assertNotNull(result);
        assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
        assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_decepticons_if_have_more_skill(){
        DECEPTICON_FIRST = Transformers.builder().id(2L).name("1 Decepticon").team(TransformerType.DECEPTICONS.type).skill(10).courage(4).strength(3).build();
        when(transformersRepository.findAllById(Arrays.asList(2L,3L))).thenReturn(Arrays.asList(AUTOBOT_FIRST, DECEPTICON_FIRST));
        when(iBattleRuleExecutor.executeAll(anyList(),anyList())).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam("D").build());
        TransformersBattleResponseDTO result = transformerBattleService.battleWinners(Arrays.asList(2L,3L));

        assertNotNull(result);
        assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
        assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_tie_if_both_teams_have_same_skill_and_same_courage_and_strength(){
        DECEPTICON_FIRST = Transformers.builder().id(2L).name("1 Decepticon").team(TransformerType.DECEPTICONS.type).skill(9).courage(4).strength(3).build();
        when(transformersRepository.findAllById(Arrays.asList(2L,3L))).thenReturn(Arrays.asList(AUTOBOT_FIRST, DECEPTICON_FIRST));
        when(iBattleRuleExecutor.executeAll(anyList(),anyList())).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam("Tie").build());
        TransformersBattleResponseDTO result = transformerBattleService.battleWinners(Arrays.asList(2L,3L));
        assertNotNull(result);
        assertEquals("Tie", result.getWinnerTeam());
        assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }


    @Test
    public void should_return_autobots_if_optimus_prime_present(){
        when(transformersRepository.findAllById(Arrays.asList(2L,3L))).thenReturn(Arrays.asList(OPTIMUS_PRIME, DECEPTICON_FIRST));
        when(iBattleRuleExecutor.executeAll(anyList(),anyList())).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam("A").build());
        TransformersBattleResponseDTO result = transformerBattleService.battleWinners(Arrays.asList(2L,3L));
        assertNotNull(result);
        assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
        assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }


}
