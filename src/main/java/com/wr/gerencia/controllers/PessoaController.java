package com.wr.gerencia.controllers;

import com.wr.gerencia.entities.Pessoa;
import com.wr.gerencia.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        List<Pessoa> list = service.listarPessoas();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> buscaPorId(@PathVariable Long id){
        Pessoa obj = service.consultaUmaPessoa(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa obj){
        obj = service.novaPessoa(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<Pessoa> editar(@PathVariable long id, @RequestBody Pessoa obj){
        obj = service.editarPessoa(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
