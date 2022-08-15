package com.netocampana.repositories;

import com.netocampana.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
}
