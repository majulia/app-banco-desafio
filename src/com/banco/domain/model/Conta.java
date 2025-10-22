package com.banco.domain.model;

import com.banco.domain.model.exceptions.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String numero;
    private Cliente cliente;
    private double saldo;
    private double limite;
    private List<Transacao> historico = new ArrayList<>();


    public Conta(double limite, double saldo, Cliente cliente, String numero) {
        this.limite = limite;
        this.saldo = saldo;
        this.cliente = cliente;
        this.numero = numero;
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
        historico.add(new Transacao("Transferência para " + destino.getNumero(), valor));
    }


    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return historico;
    }

}
