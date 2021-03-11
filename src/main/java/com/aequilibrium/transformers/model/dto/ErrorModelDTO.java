package com.aequilibrium.transformers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModelDTO {

    Integer code;
    String message;
}
