package com.banco.application.repository;

import com.banco.domain.model.Conta;

import java.time.LocalDate;

public interface OperacoesRepository {
    Conta criarConta(String numero, String nomeCliente, String cpf, LocalDate dataNascimento, double saldo);
    void depositar(String numeroConta, double valor);
    void sacar(String numeroConta, double valor);
    void transferir(String contaOrigem, String contaDestino, double valor);
    void exportarTransacoes(String numeroConta, String caminhoArquivo);
}
