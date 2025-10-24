package com.banco.adapters.in.console;

import com.banco.application.exceptions.OperacoesExceptions;
import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class SacarOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public SacarOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getNomeOpcao() {
        return "Realizar um Saque";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numero da Conta (xxxx-x): ");
        String numeroConta = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        try{
            System.out.println("Tentando sacar R$" + valor + "...");
            Thread.sleep(2000);
            System.out.println("Saque realizado com sucesso!");
        operacoesService.sacar(numeroConta, valor);
        } catch (OperacoesExceptions e){
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
