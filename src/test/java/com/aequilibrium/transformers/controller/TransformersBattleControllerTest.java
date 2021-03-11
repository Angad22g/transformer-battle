package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.dto.TransformerBattleRequestDTO;
import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.aequilibrium.transformers.service.TransformerBattleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TransformersBattleController.class)
public class TransformersBattleControllerTest {

    public static final String PATH = "/battle";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransformerBattleService transformersBattleService;

    @Before
    public void setup() {
        when(transformersBattleService.battleWinners(Arrays.asList(1L, 2L))).thenReturn(null);
        when(transformersBattleService.battleWinners(Arrays.asList(3L, 4L))).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam(TransformerType.AUTOBOTS.type).build());
    }

    @Test
    public void should_return_non_content_if_ids_dont_exist_when_transformersBattle() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(TransformerBattleRequestDTO
                .builder()
                .transformersIds(Arrays.asList(1L, 2L))
                .build());

        this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_return_sucess_when_transformersBattle() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(TransformerBattleRequestDTO
                .builder()
                .transformersIds(Arrays.asList(3L, 4L))
                .build());

        this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
