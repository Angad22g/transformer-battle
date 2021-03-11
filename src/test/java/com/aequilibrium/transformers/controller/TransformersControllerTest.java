package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.dto.TransformerDetailsDTO;
import com.aequilibrium.transformers.service.TransformerOperationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TransformersController.class)
public class TransformersControllerTest {
    public static final String PATH = "/transformers";

    public static final String NAME = "Optimus Prime";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransformerOperationService transformersService;

    private ObjectWriter ow;
    @Before
    public void setup() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.ow = mapper.writer().withDefaultPrettyPrinter();
        doNothing().when(transformersService).createTransformer(any());
        doNothing().when(transformersService).updateTransformer(any());

        when(transformersService.getTransformers())
                .thenReturn(Arrays.asList(TransformerDetailsDTO.builder().id(1L).name(NAME).build()));

        doThrow(EmptyResultDataAccessException.class).when(transformersService).deleteTransformer(2L);

    }


    @Test
    public void should_return_2xxResponseStatus_when_createTransformer() throws Exception {
        String requestJson = ow
                .writeValueAsString(TransformerDetailsDTO.builder().name(NAME).team(TransformerType.AUTOBOTS.type).build());

        this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }


    @Test
    public void should_return_error_no_name_when_createTransformer() throws Exception {

        String requestJson = ow.writeValueAsString(TransformerDetailsDTO.builder().team(TransformerType.AUTOBOTS.type).build());

        this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is4xxClientError());

    }



    @Test
    public void should_return_invalid_transformerType_createTransformer() throws Exception {

        String requestJson = ow.writeValueAsString(TransformerDetailsDTO.builder().team("H").id(1L).build());

        this.mockMvc.perform(post(PATH+"/1L").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void should_return_2xxResponseStatus_when_updateTransformer() throws Exception {
        String requestJson = ow
                .writeValueAsString(TransformerDetailsDTO.builder().name(NAME).team(TransformerType.AUTOBOTS.type).build());

        this.mockMvc.perform(put(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void should_return_2xxResponseStatus_when_deleteTransformer() throws Exception {

        this.mockMvc.perform(delete(PATH + "/1").contentType(APPLICATION_JSON_UTF8)).andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(transformersService, times(1)).deleteTransformer((1L));

    }

    @Test
    public void should_return_2xxResponseStatus_when_getTransformers() throws Exception {
        String requestJson = ow
                .writeValueAsString(TransformerDetailsDTO.builder().name(NAME).team(TransformerType.AUTOBOTS.type).build());

        this.mockMvc.perform(get(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
