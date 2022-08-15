package com.netocampana.mapper;

import com.netocampana.dtos.DespesaDTO;
import com.netocampana.dtos.ReceitaDTO;
import com.netocampana.entities.Despesa;
import com.netocampana.entities.Receita;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.netocampana.utils.UtilsConvert.dateToString;
import static com.netocampana.utils.UtilsConvert.stringToDate;

@Component
public class Mapper {

    public ReceitaDTO toReceitaDto(Receita receita) {
        String description = receita.getDescription();
        BigDecimal valorReceita = receita.getValorReceita();
        LocalDate data = receita.getData();
        return new ReceitaDTO(description, valorReceita,dateToString(data));
    }

    public DespesaDTO toDespesaDto(Despesa despesa) {
        String description = despesa.getDescription();
        BigDecimal valorReceita = despesa.getValorDespesa();
        LocalDate data = despesa.getData();
        return new DespesaDTO(description, valorReceita,dateToString(data));
    }

    public Despesa toDespesa(DespesaDTO despesaDto) {
        return new Despesa(despesaDto.getDescription(), despesaDto.getValorDespesa(), stringToDate(despesaDto.getData()));
    }


    public Receita toReceita(ReceitaDTO receitaDto) {
        return new Receita(receitaDto.getDescription(), receitaDto.getValorReceita(), stringToDate(receitaDto.getData()));
    }



}
