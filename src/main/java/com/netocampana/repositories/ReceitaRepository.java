package com.netocampana.repositories;

import com.netocampana.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita,Integer> {


    @Query(value="SELECT * FROM receita r WHERE r.data >= :startDate AND r.data <= :endDate", nativeQuery=true)
    List<Receita> findBetweenTime(LocalDate startDate, LocalDate endDate);

}
