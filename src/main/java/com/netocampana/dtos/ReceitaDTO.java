package com.netocampana.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {

    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal valorReceita;

    private String data;

}