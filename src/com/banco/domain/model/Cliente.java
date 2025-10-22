package com.banco.domain.model;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;


    public Cliente(String nomeCliente, String cpf, LocalDate dataNascimento) {
        nome = nomeCliente;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}
