package com.banco.adapters.in.console;

import com.banco.application.exceptions.OperacoesExceptions;
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

        System.out.print("Numero da Conta com dígito(xxxx-x): ");
        String numeroConta = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        try {
            System.out.println("Processando o depósito...");
            Thread.sleep(2000);
            operacoesService.depositar(numeroConta, valor);

        } catch (
                OperacoesExceptions e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
