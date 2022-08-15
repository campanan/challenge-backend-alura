package com.netocampana.services;

import com.netocampana.dtos.DespesaDTO;
import com.netocampana.entities.Despesa;
import com.netocampana.mapper.Mapper;
import com.netocampana.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public Despesa save (DespesaDTO despesaDto) {
        Despesa despesaNew = mapper.toDespesa(despesaDto);
        return despesaRepository.save(despesaNew);
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
