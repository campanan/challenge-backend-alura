package com.netocampana.controllers;

import com.netocampana.dtos.ReceitaDTO;
import com.netocampana.entities.Receita;
import com.netocampana.mapper.Mapper;
import com.netocampana.services.ReceitaService;
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
@RequestMapping("/receitas")
public class ReceitaController {


    @Autowired
    private final ReceitaService receitaService;

    @Autowired
    private Mapper mapper;


    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody @Valid ReceitaDTO receitaDto){

        return receitaService.save(receitaDto);
    }

    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listAll(){
        return ResponseEntity.ok().body(receitaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Integer id){
        return receitaService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReceita(@PathVariable Integer id, @RequestBody ReceitaDTO receitaDto) {
        receitaService.update(id, receitaDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        receitaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
