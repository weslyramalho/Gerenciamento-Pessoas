package com.wr.gerencia.controllers;

import com.wr.gerencia.entities.Endereco;
import com.wr.gerencia.entities.Pessoa;
import com.wr.gerencia.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<Endereco> inserir(@RequestBody Endereco obj){
        obj = service.novoEndereco(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco(){
        List<Endereco> list = service.listarEndereco();
        return ResponseEntity.ok().body(list);
    }
}
