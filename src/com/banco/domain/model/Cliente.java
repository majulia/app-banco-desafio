package com.banco.domain.model;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;


    public Cliente(String nomeCliente, String cpf, String telefone, LocalDate dataNascimento) {
        nome = nomeCliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}
