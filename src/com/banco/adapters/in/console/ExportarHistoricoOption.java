package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class ExportarHistoricoOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public ExportarHistoricoOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getOpcaoNumerica() {
        return "5";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Conta: ");
        String num = scanner.next();
        System.out.print("Arquivo CSV (ex: historico.csv): ");
        String arq = scanner.next();
        operacoesService.exportarTransacoes(num, arq);
        System.out.println("Histórico exportado com sucesso!");
    }
}
