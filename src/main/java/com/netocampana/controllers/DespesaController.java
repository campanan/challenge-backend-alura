package com.netocampana.controllers;

import com.netocampana.dtos.DespesaDTO;
import com.netocampana.entities.Despesa;
import com.netocampana.mapper.Mapper;
import com.netocampana.services.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/despesas")
public class DespesaController {


    @Autowired
    private final DespesaService despesaService;

    @Autowired
    private Mapper mapper;


    @PostMapping
    public ResponseEntity<Despesa> save(@RequestBody @Valid DespesaDTO despesaDto){

        Despesa despesa = despesaService.save(despesaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(despesa);
    }

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> listAll(){
        return ResponseEntity.ok().body(despesaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDTO> findById(@PathVariable Integer id){
        return despesaService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDespesa(@PathVariable Integer id, @RequestBody DespesaDTO despesaDto) {
        despesaService.update(id, despesaDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        despesaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
