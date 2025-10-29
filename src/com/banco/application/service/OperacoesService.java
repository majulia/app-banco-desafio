package com.banco.application.service;

import com.banco.application.exceptions.OperacoesExceptions;
import com.banco.application.repository.ContaRepository;
import com.banco.application.repository.OperacoesRepository;
import com.banco.domain.model.Cliente;
import com.banco.domain.model.Conta;
import com.banco.domain.model.TipoConta;
import com.banco.infra.export.ExporterCsv;
import com.banco.application.GeradorDeNumeros;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;


public class OperacoesService implements OperacoesRepository {

    private final ContaRepository contaRepository;
    private final ExporterCsv exporterCsv;
    private GeradorDeNumeros geradorDeNumeros;
    private final Random random = new Random();

    public OperacoesService(ContaRepository contaRepository, ExporterCsv exporterCsv) {
        this.contaRepository = contaRepository;
        this.exporterCsv = exporterCsv;
    }


    @Override
    public Conta criarConta(String nomeCliente, String cpf, String telefone, LocalDate dataNascimento, double saldo, TipoConta tipoConta) {
        double limiteIncialConta =  GeradorDeNumeros.gerarLimiteConta();
        String agencia = GeradorDeNumeros.gerarAgencia();
        String numeroDaConta = GeradorDeNumeros.gerarNumeroConta();
        Cliente cliente = new Cliente(nomeCliente, cpf, telefone, dataNascimento);
        Conta conta = new Conta(limiteIncialConta, saldo, cliente, agencia, numeroDaConta, tipoConta);
        contaRepository.salvar(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("==== Dados da conta ====");
        System.out.println(conta.toString());
        return conta;
    }

    @Override
    public void consultarSaldo(String numeroConta) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        if (conta == null){
           throw new OperacoesExceptions("Conta nº " + numeroConta + " não encontrada");
        }
        System.out.println("==== Consultando os dados atualizados ====");
        System.out.println(conta.toString());
    }

    @Override
    public void depositar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        if (conta == null){
           throw new OperacoesExceptions("Conta nº " + numeroConta + " não encontrada");
        } else {
            conta.depositar(valor);
            System.out.println("Deposito realizado com sucesso!");
        }
        System.out.println("==== Dados atualizados ====");
        System.out.println(conta.toString());
    }

    @Override
    public void sacar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        if (conta == null){
            throw new OperacoesExceptions("Conta nº " + numeroConta + " não encontrada");
        }
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso!");
        System.out.println(conta.toString());
    }

    @Override
    public void transferir(String contaOrigem, String contaDestino, double valor) {
        LocalTime horaAtual = LocalTime.now();
        LocalTime limiteHorario = LocalTime.of(22,30);
        if (horaAtual.isAfter(limiteHorario)  && valor > 250){
            System.out.println("Transferencia não realizada!");
            System.out.println("Limite excedido: Transferencia após 22:30 tem limite de R$: 250");
        } else {
        Conta origem = contaRepository.buscarContaPorNumero(contaOrigem);
        Conta destino = contaRepository.buscarContaPorNumero(contaDestino);
        if (origem != null && destino != null) {
            origem.transferir(destino, valor);
            System.out.println("Transferencia realizada com sucesso!");

            System.out.println("==== Dados atualizados ====");
            System.out.println(origem.toString());
        } else {
            throw new OperacoesExceptions("Conta de Origem ou Destino não encontrada!");
        }

        }
    }

    public void alterarLimite(String numeroConta){
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        if (conta == null){
           throw new OperacoesExceptions("Conta " + numeroConta + " não encontrada");
        } else {
            double novoLimite = conta.getSaldo() + GeradorDeNumeros.gerarLimiteConta();
            conta.setLimite(novoLimite);
            String novoLimiteFormatado = String.format("%.2f", novoLimite);
            System.out.println("Limite Alterado com sucesso! Novo limite da conta: "+ novoLimiteFormatado);
        }
        contaRepository.salvar(conta);
    }

    @Override
    public void exportarTransacoes(String numeroConta, String caminhoArquivo) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        if (conta == null){
            throw new OperacoesExceptions("Conta nº " + numeroConta + " não encontrada");
        } else {
            exporterCsv.exportar(conta.getHistorico(), caminhoArquivo);
            System.out.println("Histórico exportado com sucesso!");
        }
    }


}
