package com.netocampana.services;

import com.netocampana.dtos.DespesaDTO;
import com.netocampana.entities.Despesa;
import com.netocampana.entities.Receita;
import com.netocampana.mapper.Mapper;
import com.netocampana.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.netocampana.utils.UtilsConvert.stringToDate;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public ResponseEntity<Despesa> save (DespesaDTO despesaDto) {
        LocalDate dataAtual = (stringToDate(despesaDto.getData()));
        YearMonth yearMonth = YearMonth.of(dataAtual.getYear(), dataAtual.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();

        List<Despesa> listaDespesaMes = despesaRepository.findBetweenTime(firstOfMonth, lastDay);

        for(Despesa despesa : listaDespesaMes){
            if(despesa.getDescription().equals(despesaDto.getDescription())){
                return ResponseEntity.badRequest().build();
            }
        }
        Despesa despesa = mapper.toDespesa(despesaDto);
        despesaRepository.save(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(despesa);
    }

    public List<DespesaDTO> listAll() {

        List<Despesa> listaDespesas = despesaRepository.findAll();
        List<DespesaDTO> listaDTO = new ArrayList<>();

        for(Despesa despesa : listaDespesas){

            listaDTO.add(mapper.toDespesaDto(despesa));
        }
        return listaDTO;
    }

    public ResponseEntity<DespesaDTO> findById(Integer id) {

        Optional<Despesa> despesaOptional = despesaRepository.findById(id);
        if(despesaOptional.isPresent()) {
            Despesa despesa = new Despesa(despesaOptional.get().getId(), despesaOptional.get().getDescription(),despesaOptional.get().getValorDespesa(), despesaOptional.get().getData());
            return ResponseEntity.ok(mapper.toDespesaDto(despesa));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    public void update(Integer id,DespesaDTO despesaDto) {
        Despesa despesa = mapper.toDespesa(despesaDto);
        Despesa despesaAtualizada = new Despesa(id,despesa.getDescription(),despesa.getValorDespesa(),despesa.getData());
        despesaRepository.save(despesaAtualizada);
    }

    public void delete(Integer id) {
        Despesa despesa = despesaRepository.getReferenceById(id);
        despesaRepository.delete(despesa);
    }
}
