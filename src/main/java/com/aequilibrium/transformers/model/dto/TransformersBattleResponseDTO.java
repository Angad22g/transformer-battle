package com.aequilibrium.transformers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransformersBattleResponseDTO {

    private Integer numberOfBattles;
    private String winnerTeam;
    private List<String> survivorsLosingTeam;
}
