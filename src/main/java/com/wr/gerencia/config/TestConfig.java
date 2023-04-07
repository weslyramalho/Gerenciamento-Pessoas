package com.wr.gerencia.config;

import com.wr.gerencia.entities.Endereco;
import com.wr.gerencia.entities.Pessoa;
import com.wr.gerencia.repositories.EnderecoRepository;
import com.wr.gerencia.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Endereco end1 = new Endereco(null, "Rua das Garças", "69534-444", 297, "Buriticordea", "Mangazin");
        Endereco end2 = new Endereco(null, "Rua dos Guris", "69534-444", 397, "Buriticordea", "Mangazin");
        Endereco end3 = new Endereco(null, "Rua dos Guris", "69534-444", 397, "Buriticordea", "Mangazin");

        Pessoa ps1 = new Pessoa(null, "Manoel Gomes Padin", "02/081978");
        pessoaRepository.save(ps1);

    }
}
