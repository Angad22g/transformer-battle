package com.aequilibrium.transformers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransformerDetailsDTO implements Serializable {

    private static final int MIN_RANGE = 1;

    private static final int MAX_RANGE = 10;

    private Long id;

    @NotBlank(message = "{transformers.name.validation}")
    private String name;

    @NotBlank(message = "{transformers.team.validation}")
    private String team;

    @Min(value = MIN_RANGE, message = "{strength.range}")
    @Max(value = MAX_RANGE, message = "{strength.range}")
    private Integer strength;

    @Min(value = MIN_RANGE, message = "{intelligence.range}")
    @Max(value = MAX_RANGE, message = "{intelligence.range}")
    private Integer intelligence;

    @Min(value = MIN_RANGE, message = "{speed.range}")
    @Max(value = MAX_RANGE, message = "{speed.range}")
    private Integer speed;

    @Min(value = MIN_RANGE, message = "{endurance.range}")
    @Max(value = MAX_RANGE, message = "{endurance.range}")
    private Integer endurance;

    @Min(value = MIN_RANGE, message = "{rank.range}")
    @Max(value = MAX_RANGE, message = "{rank.range}")
    private Integer rank;

    @Min(value = MIN_RANGE, message = "{courage.range}")
    @Max(value = MAX_RANGE, message = "{courage.range}")
    private Integer courage;

    @Min(value = MIN_RANGE, message = "{firepower.range}")
    @Max(value = MAX_RANGE, message = "{firepower.range}")
    private Integer firepower;

    @Min(value = MIN_RANGE, message = "{skill.range}")
    @Max(value = MAX_RANGE, message = "{skill.range}")
    private Integer skill;

}
