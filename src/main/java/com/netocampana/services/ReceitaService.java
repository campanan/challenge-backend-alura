package com.netocampana.services;

import com.netocampana.dtos.ReceitaDTO;
import com.netocampana.entities.Receita;
import com.netocampana.mapper.Mapper;
import com.netocampana.repositories.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.netocampana.utils.UtilsConvert.dateToString;
import static com.netocampana.utils.UtilsConvert.stringToDate;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    @Autowired
    private final ReceitaRepository receitaRepository;

    @Autowired
    private Mapper mapper;



    @Transactional
    public ResponseEntity<Receita> save(ReceitaDTO receitaDto) {
        LocalDate dataAtual = (stringToDate(receitaDto.getData()));
        YearMonth yearMonth = YearMonth.of(dataAtual.getYear(), dataAtual.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();


        List<Receita> listaReceitasMes = receitaRepository.findBetweenTime(firstOfMonth, lastDay);

        for(Receita receita : listaReceitasMes){
            if(receita.getDescription().equals(receitaDto.getDescription())){
                return ResponseEntity.badRequest().build();
            }
        }
        Receita receita = mapper.toReceita(receitaDto);
        receitaRepository.save(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(receita);

    }

    public List<ReceitaDTO> listAll() {

        List<Receita> listaReceitas = receitaRepository.findAll();
        List<ReceitaDTO> listaDTO = new ArrayList<>();

        for(Receita receita : listaReceitas){

            listaDTO.add(mapper.toReceitaDto(receita));
        }

        return listaDTO;
    }

    public ResponseEntity<ReceitaDTO> findById(Integer id) {

        Optional<Receita> receitaOptional = receitaRepository.findById(id);
        if(receitaOptional.isPresent()) {
            Receita receita = new Receita(receitaOptional.get().getId(), receitaOptional.get().getDescription(),receitaOptional.get().getValorReceita(), receitaOptional.get().getData());
            return ResponseEntity.ok(mapper.toReceitaDto(receita));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    public void update(Integer id,ReceitaDTO receitaDto) {

        Receita receita = mapper.toReceita(receitaDto);

        Receita receitaAtualizada = new Receita(id,receita.getDescription(),receita.getValorReceita(),receita.getData());
        receitaRepository.save(receitaAtualizada);
    }

    public void delete(Integer id) {
        Receita receita = receitaRepository.getReferenceById(id);
        receitaRepository.delete(receita);
    }
}
