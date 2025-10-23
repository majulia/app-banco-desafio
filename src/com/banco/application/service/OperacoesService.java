package com.banco.application.service;

import com.banco.application.repository.ContaRepository;
import com.banco.application.repository.OperacoesRepository;
import com.banco.domain.model.Cliente;
import com.banco.domain.model.Conta;
import com.banco.infra.export.ExporterCsv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
       return   100 + (5500 * random.nextDouble());
    }

    private String gerarAgencia(){
        int numero = random.nextInt(99) + 1;
        return String.format("00%02d", numero);
    }

    private String gerarNumeroConta(){
        int numeroConta = 1000 + random.nextInt(9000) + 1; //1000-9999 para gerar a conta
        int digito = random.nextInt(10); //0-9
        return String.format("%d-%d", numeroConta,digito);
    }


    @Override
    public Conta criarConta( String nomeCliente, String cpf, String telefone, LocalDate dataNascimento, double saldo) {
        double limiteIncialConta = gerarLimiteConta();
        String agencia = gerarAgencia();
        String numeroDaConta = gerarNumeroConta();
        Cliente cliente = new Cliente(nomeCliente, cpf, telefone, dataNascimento);
        Conta conta = new Conta(limiteIncialConta, saldo, cliente, agencia, numeroDaConta);
        contaRepository.salvar(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("==== Dados da conta ====");
        System.out.println(conta.toString());
        return conta;
    }

    @Override
    public void consultarSaldo(String numeroConta) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);

        System.out.println("==== Dados atualizados ====");
        System.out.println(conta.toString());
    }

    @Override
    public void depositar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        conta.depositar(valor);
        System.out.println("==== Dados atualizados ====");
        System.out.println(conta.toString());
    }

    @Override
    public void sacar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);
        conta.sacar(valor);
        System.out.println("==== Dados atualizados ====");
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
        origem.transferir(destino, valor);
        System.out.println("==== Dados atualizados ====");
        System.out.println(origem.toString());
        }
    }

    @Override
    public void exportarTransacoes(String numeroConta, String caminhoArquivo) {
        Conta conta = contaRepository.buscarContaPorNumero(numeroConta);

        exporterCsv.exportar(conta.getHistorico(), caminhoArquivo);
    }


}
