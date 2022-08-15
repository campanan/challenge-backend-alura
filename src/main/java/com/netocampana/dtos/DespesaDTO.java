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
public class DespesaDTO {

    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal valorDespesa;

    private String data;

}