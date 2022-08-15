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
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "descrição")
    private String description;
    private BigDecimal valorReceita;
    private LocalDate data;

    public Receita(String description, BigDecimal valorReceita, LocalDate data) {
        this.description = description;
        this.valorReceita = valorReceita;
        this.data = data;
    }
}
