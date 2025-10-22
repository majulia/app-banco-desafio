package com.banco.application.service;

import com.banco.application.repository.ContaRepository;
import com.banco.application.repository.OperacoesRepository;
import com.banco.domain.model.Cliente;
import com.banco.domain.model.Conta;
import com.banco.infra.export.ExporterCsv;

import java.time.LocalDate;
import java.util.Random;


public class OperacoesService implements OperacoesRepository {

    private final ContaRepository contaRepository;
    private final ExporterCsv exporterCsv;
    private final Random random = new Random();

    public OperacoesService(ContaRepository contaRepository, ExporterCsv exporterCsv) {
        this.contaRepository = contaRepository;
        this.exporterCsv = exporterCsv;
    }

    private double gerarLimiteConta(){
        return  500 + (4500 * random.nextDouble());
    }

    @Override
    public Conta criarConta(String numero, String nomeCliente, String cpf, LocalDate dataNascimento, double saldo) {
        double limiteIncialConta = gerarLimiteConta();
        Cliente cliente = new Cliente(nomeCliente, cpf, dataNascimento);
        Conta conta = new Conta(limiteIncialConta, saldo, cliente, numero);
        contaRepository.salvar(conta);
        System.out.println("Conta criada com sucesso!");
        return conta;
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
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);

        exporterCsv.exportar(conta.getHistorico(), caminhoArquivo);
    }
}
