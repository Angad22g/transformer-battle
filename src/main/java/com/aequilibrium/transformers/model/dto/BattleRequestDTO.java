package com.aequilibrium.transformers.model.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@JsonInclude(Include.NON_EMPTY)
public class BattleRequestDTO {

    @NotEmpty(message = "{notempty.battleRequest.transfomersids}")
    List<Long> transformersIds;

}
