package com.banco.application;

import java.util.Random;

public class GeradorDeNumeros {

    private static final Random random = new Random();

    public static double gerarLimiteConta(){
        return 100 + (5500 * random.nextDouble());
    }

    public static String gerarAgencia(){
        int numero = random.nextInt(99) + 1;
        return String.format("00%02d", numero);
    }

    public static String gerarNumeroConta(){
        int numeroConta = 1000 + random.nextInt(9000) + 1; //1000-9999 para gerar a conta
        int digito = random.nextInt(10); //0-9
        return String.format("%d-%d", numeroConta,digito);
    }
}
