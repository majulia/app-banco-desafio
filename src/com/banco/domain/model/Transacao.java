package com.banco.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transacao {
    private String tipo;
    private double valor;
    private LocalDate data;
    private LocalTime hora;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
    }



    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora(){
        return hora;
    }
}
