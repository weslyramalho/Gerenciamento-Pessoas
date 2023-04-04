package com.wr.gerencia.services;

import com.wr.gerencia.entities.Endereco;
import com.wr.gerencia.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    public Endereco novoEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEndereco(){
        return enderecoRepository.findAll();
    }
}
