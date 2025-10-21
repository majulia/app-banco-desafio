package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class SacarOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public SacarOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getNomeOpcao() {
        return "Sacar";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Conta: ");
        String num = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        operacoesService.sacar(num, valor);
        System.out.println("Saque realizado com sucesso!");

    }
}
