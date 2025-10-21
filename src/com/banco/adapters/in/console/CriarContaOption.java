package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class CriarContaOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public CriarContaOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getNomeOpcao() {
        return "Criar conta";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número da conta: ");
        String num = scanner.next();
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Limite inicial: ");
        double limite = scanner.nextDouble();
        operacoesService.criarConta(num, nome, limite);
        System.out.println("Conta criada com sucesso!");
    }
}
