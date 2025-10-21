package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class TransferirOption implements MenuOptions{
    private final OperacoesService operacoesService;

    public TransferirOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }


    @Override
    public String getNomeOpcao() {
        return "Transferir";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Conta origem: ");
        String origem = scanner.next();
        System.out.print("Conta destino: ");
        String destino = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        operacoesService.transferir(origem, destino, valor);
        System.out.println("Transferência feita!");
    }
}
