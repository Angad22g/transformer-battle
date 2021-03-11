package com.aequilibrium.transformers.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransformerBattleRequestDTO {

    @NotEmpty(message = "The list cant be empty")
    List<Long> transformersIds;

}
