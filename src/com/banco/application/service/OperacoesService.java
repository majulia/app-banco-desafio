package com.banco.application.service;

import com.banco.application.gateway.ContaRepository;
import com.banco.application.gateway.OperacoesRepository;
import com.banco.domain.model.Cliente;
import com.banco.domain.model.Conta;

public class OperacoesService implements OperacoesRepository {

    private final ContaRepository contaRepository;

    public OperacoesService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void criarConta(String numero, String nomeCliente, double limite) {
        Conta conta = new Conta(numero, new Cliente(nomeCliente), limite);
    }

    @Override
    public void depositar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        conta.depositar(valor);
    }

    @Override
    public void sacar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        conta.sacar(valor);
    }

    @Override
    public void transferir(String contaOrigem, String contaDestino, double valor) {
        Conta origem = contaRepository.buscarContaPorNumero(contaOrigem);
        Conta destino = contaRepository.buscarContaPorNumero(contaDestino);
        origem.transferir(destino, valor);
    }

    @Override
    public void exportarTransacoes(String numeroConta, String caminhoArquivo) {

    }
}
