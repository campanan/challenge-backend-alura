package com.netocampana.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "descrição")
    private String description;
    private BigDecimal valorDespesa;
    private LocalDate data;

    public Despesa(String description, BigDecimal valorReceita, LocalDate data) {
        this.description = description;
        this.valorDespesa = valorReceita;
        this.data = data;
    }
}
