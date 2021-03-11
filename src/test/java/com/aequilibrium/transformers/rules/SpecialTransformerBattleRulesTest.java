package com.aequilibrium.transformers.rules;

import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpecialTransformerBattleRulesTest {

    private static Transformers OPTIMUS_PRIME;
    private static Transformers PREDAKING;
    private static Transformers AUTOBOT_FIRST;
    private static Transformers DECEPTICON_FIRST;

    private static final String ALL_DESTROYED = "All Transformers are destroyed";

    @InjectMocks
    SpecialTransformerBattleRules service;

    @Before
    public void setup() {

        OPTIMUS_PRIME = Transformers.builder().id(1L).name("Optimus Prime").team(TransformerType.AUTOBOTS.type).build();
        PREDAKING = Transformers.builder().id(2L).name("Predaking").team(TransformerType.DECEPTICONS.type).build();
        AUTOBOT_FIRST = Transformers.builder().id(3L).name("1 Autobot").team(TransformerType.AUTOBOTS.type).build();
        DECEPTICON_FIRST = Transformers.builder().id(2L).name("1 Decepticon").team(TransformerType.DECEPTICONS.type).build();
    }

    @Test
    public void should_return_all_destroyed_if_optimus_prime_and_predaking_are_present_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service
                .execute(Arrays.asList(OPTIMUS_PRIME, PREDAKING, DECEPTICON_FIRST));
        assertNotNull(response);
        assertEquals(ALL_DESTROYED, response.getWinnerTeam());
    }

    @Test
    public void should_return_all_destroyed_if_optimus_prime_is_duplicated_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service.execute(Arrays.asList(OPTIMUS_PRIME, OPTIMUS_PRIME));
        assertNotNull(response);
        assertEquals(ALL_DESTROYED, response.getWinnerTeam());
    }


    @Test
    public void should_return_null_if_dont_have_any_team_lead_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service.execute(Arrays.asList(AUTOBOT_FIRST, DECEPTICON_FIRST));
        assertNull(response);
    }


    @Test
    public void should_return_all_destroyed_if_predaking_is_duplicated_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service.execute(Arrays.asList(PREDAKING, PREDAKING));
        assertNotNull(ALL_DESTROYED, response.getWinnerTeam());
    }

    @Test
    public void should_return_null_if_dont_have_any_transformers_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service.execute(Arrays.asList());
        assertNull(response);
    }

    @Test
    public void should_return_autobots_wins_if_optimus_prime_is_present_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service
                .execute(Arrays.asList(AUTOBOT_FIRST, OPTIMUS_PRIME, DECEPTICON_FIRST));
        assertNotNull(response);
        assertEquals(TransformerType.AUTOBOTS.type, response.getWinnerTeam());
    }

    @Test
    public void should_return_decepticons_wins_if_predaking_is_present_when_verifySpecialRules() {
        TransformersBattleResponseDTO response = service
                .execute(Arrays.asList(AUTOBOT_FIRST, PREDAKING, DECEPTICON_FIRST));
        assertNotNull(response);
        assertEquals(TransformerType.DECEPTICONS.type, response.getWinnerTeam());
    }
}