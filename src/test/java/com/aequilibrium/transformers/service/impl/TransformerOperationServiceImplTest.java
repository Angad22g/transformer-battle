package com.aequilibrium.transformers.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.aequilibrium.transformers.enums.TransformerType;
import com.aequilibrium.transformers.model.domain.Transformers;
import com.aequilibrium.transformers.model.dto.TransformerDetailsDTO;
import com.aequilibrium.transformers.repository.TransformersRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class TransformerOperationServiceImplTest {

    private final static String NAME = "Optimus Prime";

    @InjectMocks
    private TransformerOperationServiceImpl service;

    @Mock
    private TransformersRepository transformersRepository;

    @Before
    public void setup() {

	Transformers transformerOne = Transformers.builder().id(1L).name(NAME).team(TransformerType.AUTOBOTS.type)
		.build();

    when(transformersRepository.save(any())).thenReturn(Transformers.builder().name("Autobots").build());
	when(transformersRepository.findAll()).thenReturn(Arrays.asList(transformerOne));
    }

    @Test
    public void should_create_new_transfomers_when_createTransformer() {
	service
		.createTransformer(TransformerDetailsDTO.builder().name(NAME).team(TransformerType.AUTOBOTS.type).build());
        verify(transformersRepository, times(1)).save(any(Transformers.class));

    }

    @Test
    public void should_return_updated_transfomers_when_updateTransformer() {
	service
		.updateTransformer(TransformerDetailsDTO.builder().id(1L).name(NAME).team(TransformerType.AUTOBOTS.type).build());
        verify(transformersRepository, times(1)).save(any());
    }

    @Test
    public void should_delete_when_deleteTransformer() {
	service.deleteTransformer(1L);

	verify(transformersRepository, times(1)).deleteById(1L);
    }

    @Test
    public void should_return_list_when_getTransformers() {
	List<TransformerDetailsDTO> result = service.getTransformers();
	assertThat(result).isNotEmpty();

    }

}
