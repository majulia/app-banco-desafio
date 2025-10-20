package com.banco.application.repository;

public interface OperacoesRepository {
    void criarConta(String numero, String nomeCliente, double limite);
    void depositar(String numeroConta, double valor);
    void sacar(String numeroConta, double valor);
    void transferir(String contaOrigem, String contaDestino, double valor);
    void exportarTransacoes(String numeroConta, String caminhoArquivo);
}
