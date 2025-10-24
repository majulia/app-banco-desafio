package com.banco.adapters.in.console;

import com.banco.application.exceptions.OperacoesExceptions;
import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class AlterarLimiteOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public AlterarLimiteOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }

    @Override
    public String getNomeOpcao() {
        return "Alterar Limite de Crédito";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numero da Conta: ");
        String numeroConta = scanner.next();
        try {
            System.out.println("Processando a operação...");
            Thread.sleep(2000);
            operacoesService.alterarLimite(numeroConta);
        }catch (OperacoesExceptions e)
        {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
