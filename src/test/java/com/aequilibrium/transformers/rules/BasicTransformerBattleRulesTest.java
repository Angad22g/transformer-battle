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
public class BasicTransformerBattleRulesTest {


    private static Transformers OPTIMUS_PRIME;
    private static Transformers PREDAKING;
    private static Transformers SOUNDWAVE;
    private static Transformers BLUESTREAK;
    private static Transformers HUBCAP;

    @InjectMocks
    private BasicTransformerBattleRules service;

    @Before
    public void setup() {
        OPTIMUS_PRIME = Transformers.builder().id(4L).name("Optimus Prime").rank(3).team(TransformerType.AUTOBOTS.type).build();
        PREDAKING = Transformers.builder().id(5L).name("Predaking").rank(4).team(TransformerType.DECEPTICONS.type).build();
        SOUNDWAVE = new Transformers(1L, "Soundwave", TransformerType.DECEPTICONS.type, 8, 9, 2, 6, 7, 5, 6, 10);
        BLUESTREAK = new Transformers(2L, "Bluestreak", TransformerType.AUTOBOTS.type, 6, 6, 7, 9, 5, 2, 9, 7);
        HUBCAP = new Transformers(3L, "Hubcap", TransformerType.AUTOBOTS.type, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test
    public void should_return_decepticon_wins_if_decepticon_is_stronger_than_autobot_when_execute() {
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(SOUNDWAVE, BLUESTREAK));
        assertNotNull(result);
        assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
        assertEquals(1, result.getNumberOfBattles().intValue());
    }

    @Test
    public void should_return_autobot_wins_if_autobot_is_stronger_than_decepticon_when_execute() {

        Transformers autobot = new Transformers(1L, "Autobot Stronger", TransformerType.AUTOBOTS.type, 8, 9, 2, 6, 7,
                8, 8, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(SOUNDWAVE, autobot));
        assertNotNull(result);
        assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
        assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_tie__if_autobot_is_stronger_than_autobot_when_execute() {

        Transformers autobot = new Transformers(1L, "Autobot Stronger", TransformerType.AUTOBOTS.type, 8, 9, 2, 6, 7,
                5, 6, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(SOUNDWAVE, autobot));
        assertNotNull(result);
        assertEquals("Tie",result.getWinnerTeam());
        assertEquals("1", result.getNumberOfBattles().toString());
    }

    @Test
    public void should_return_deceptipcon_wins_if_deceptipcon_is_more_courageous_than_autobot_when_execute() {

        Transformers decepticon = new Transformers(1L, "Decepticon courageous", TransformerType.DECEPTICONS.type, 8,
                9, 2, 6, 7, 9, 6, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(decepticon, HUBCAP));
        assertNotNull(result);
		assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
		assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_deceptipcon_wins_if_deceptipcon_has_more_strength_than_autobot_when_execute() {

        Transformers decepticon = new Transformers(1L, "Decepticon Stronger", TransformerType.DECEPTICONS.type, 8, 9,
                2, 6, 7, 4, 6, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(decepticon, HUBCAP));
        assertNotNull(result);
		assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
		assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_autobot_wins_if_autobot_is_more_courageous_than_decepticon_when_execute() {

        Transformers autobot = new Transformers(1L, "autobot courageous", TransformerType.AUTOBOTS.type, 8, 9, 2, 6,
                7, 9, 6, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(autobot, SOUNDWAVE));
        assertNotNull(result);
		assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
		assertEquals(Integer.valueOf(1),result.getNumberOfBattles());
    }

    @Test
    public void should_return_autobot_wins_if_autobot_has_more_strength_than_decepticon__when_execute() {

        Transformers decepticon = new Transformers(1L, "decepticon", TransformerType.DECEPTICONS.type, 1, 9, 2, 6, 7,
                4, 6, 10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(decepticon, BLUESTREAK));
        assertNotNull(result);
		assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
    }

    @Test
    public void should_return_autobot_wins_if_autobot_has_more_skill_than_decepticon__when_execute() {

        Transformers decepticon = new Transformers(1L, "decepticon", TransformerType.DECEPTICONS.type, 5, 9, 2, 6, 7,
                4, 6, 4);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(decepticon, BLUESTREAK));
        assertNotNull(result);
		assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
    }

    @Test
    public void should_return_decepticon_wins_if_overall_rate_better_than_autobots_when_execute() {

        Transformers decepticon = new Transformers(1L, "decepticon", TransformerType.DECEPTICONS.type, 4, 4, 4, 4, 4,
                4, 6, 4);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(decepticon, HUBCAP));
        assertNotNull(result);
		assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
    }

    @Test
    public void should_return_decepticons_wins_with_two_fighters_when_execute() {

        Transformers decepticon = new Transformers(1L, "decepticon", TransformerType.DECEPTICONS.type, 4, 4, 4, 4, 4,
                4, 6, 4);

        TransformersBattleResponseDTO result = service
                .execute(Arrays.asList(decepticon, BLUESTREAK, HUBCAP, SOUNDWAVE));
        assertNotNull(result);
		assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
		assertEquals(2,result.getNumberOfBattles().intValue());

    }

    @Test
    public void should_return_one_losing_survivor_and_decepticons_wins_if_has_3_fighter_when_execute() {
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(BLUESTREAK, HUBCAP, SOUNDWAVE));
        assertNotNull(result);
		assertEquals(TransformerType.DECEPTICONS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
		assertEquals(1,result.getSurvivorsLosingTeam().size());
    }

    @Test
    public void should_return_zero_losing_survivor_and_autobot_wins_if_has_3_fighter_when_execute() {
        Transformers strongDecepticon = new Transformers(3L, "dec", TransformerType.DECEPTICONS.type, 6, 6, 7, 8, 5,
                2, 9, 7);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(BLUESTREAK, HUBCAP, strongDecepticon));
        assertNotNull(result);
		assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
		assertEquals(0,result.getSurvivorsLosingTeam().size());
    }

    @Test
    public void should_return_one_losing_survivor_and_autobot_wins_if_has_3_fighter_when_execute() {
        Transformers weakDecepticon = new Transformers(3L, "dec", TransformerType.DECEPTICONS.type, 4, 4, 4, 4, 4, 4,
                4, 4);
        Transformers strongDecepticon = new Transformers(3L, "dec", TransformerType.DECEPTICONS.type, 6, 6, 7, 8, 5,
                2, 9, 7);
        TransformersBattleResponseDTO result = service
                .execute(Arrays.asList(BLUESTREAK, weakDecepticon, strongDecepticon));
        assertNotNull(result);
		assertEquals(TransformerType.AUTOBOTS.type, result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
		assertEquals(1,result.getSurvivorsLosingTeam().size());
    }

    @Test
    public void should_return_tie_if_has_3_fighter_when_execute() {
        Transformers autobot = new Transformers(1L, "autobot", TransformerType.AUTOBOTS.type, 8, 9, 2, 6, 7, 5, 6,
                10);
        TransformersBattleResponseDTO result = service.execute(Arrays.asList(autobot, HUBCAP, SOUNDWAVE));
        assertNotNull(result);
		assertEquals("Tie", result.getWinnerTeam());
		assertEquals(1,result.getNumberOfBattles().intValue());
    }



}
