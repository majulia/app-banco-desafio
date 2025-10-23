package com.banco.adapters.in.console;

import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class ConsultaSaldoOption implements MenuOptions {
    private final OperacoesService operacoesService;

    public ConsultaSaldoOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }

    @Override
    public String getNomeOpcao() {
        return "Consulta de Saldo";
    }

    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numero da Conta: ");
        String numeroConta = scanner.next();
    operacoesService.consultarSaldo(numeroConta);
    }
}
