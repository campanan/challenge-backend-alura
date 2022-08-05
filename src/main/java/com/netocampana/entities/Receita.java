package com.netocampana.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "descrição", unique = true)
    private String description;
    private float valorReceita;
    private LocalDateTime data;

    public Receitas(String description, float valorReceita, LocalDateTime data) {
        this.description = description;
        this.valorReceita = valorReceita;
        this.data = data;
    }
}
