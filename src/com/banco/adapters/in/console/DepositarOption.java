package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class DepositarOption implements MenuOptions{
private final OperacoesService operacoesService;

    public DepositarOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getNomeOpcao() {
        return "Fazer um depósito";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Conta: ");
        String num = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        operacoesService.depositar(num, valor);
        System.out.println("Depósito realizado com sucesso!");
    }
}
