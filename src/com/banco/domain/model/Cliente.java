package com.banco.domain.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nomeCliente) {
        nome = nomeCliente;
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
}
