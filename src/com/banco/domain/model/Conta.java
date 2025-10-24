package com.banco.domain.model;

import com.banco.domain.model.exceptions.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String agencia;
    private String numeroDaConta;
    private Cliente cliente;
    private double saldo;
    private double limite;
    private List<Transacao> historico = new ArrayList<>();


    public Conta(double limite, double saldo, Cliente cliente, String agencia, String numeroDaConta) {
        this.limite = limite;
        this.saldo = saldo;
        this.cliente = cliente;
        this.agencia = agencia;
        this.numeroDaConta = numeroDaConta;
    }

    public void depositar(double valor) {
        saldo += valor;
        historico.add(new Transacao("Depósito", valor));
    }

    public void sacar(double valor) {
        if (valor > saldo + limite)
            throw new SaldoInsuficienteException("Saldo insuficiente!");
        saldo -= valor;
        historico.add(new Transacao("Saque", valor));
    }

    public void transferir(Conta destino, double valor) {
        this.sacar(valor);
        destino.depositar(valor);
        historico.add(new Transacao("Transferência para " + destino.getNumeroDaConta(), valor));
    }


    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return historico;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString(){
        return String.format("Agencia: %s Conta nº: %s\nTitular: %s\nSaldo: R$%.2f\nLimite: R$%.2f",
                agencia, numeroDaConta, cliente.getNome(), saldo, limite);
    }

}
