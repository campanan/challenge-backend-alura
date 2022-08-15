package com.netocampana.repositories;

import com.netocampana.entities.Despesa;
import com.netocampana.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    @Query(value="SELECT * FROM despesa d WHERE d.data >= :startDate AND d.data <= :endDate", nativeQuery=true)
    List<Despesa> findBetweenTime(LocalDate startDate, LocalDate endDate);
}
