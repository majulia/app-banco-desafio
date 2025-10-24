package com.banco.adapters.in.console;

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
        operacoesService.alterarLimite(numeroConta);
    }
}
