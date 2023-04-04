package com.wr.gerencia.services;

import com.wr.gerencia.entities.Pessoa;
import com.wr.gerencia.repositories.PessoaRepository;
import com.wr.gerencia.services.exceptions.DatabaseException;
import com.wr.gerencia.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa novaPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public Pessoa consultaUmaPessoa(Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public Pessoa editarPessoa(Long id, Pessoa pessoa){
        try {
            Pessoa pes = pessoaRepository.getReferenceById(id);
            editarDados(pes, pessoa);
            return pessoaRepository.save(pes);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
    public List<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public void deletar(Long id){
        try{
            pessoaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void editarDados(Pessoa pes, Pessoa pessoa){
        pes.setNome(pessoa.getNome());
        pes.setDataNascimento(pessoa.getDataNascimento());
        // pes.setEnderecoPrincipal(pessoa.getEnderecoPrincipal());
        // pes.setEndereco(pessoa.getEndereco());
    }

}
