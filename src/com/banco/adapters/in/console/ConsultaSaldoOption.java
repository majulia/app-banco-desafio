package com.banco.adapters.in.console;

import com.banco.application.exceptions.OperacoesExceptions;
import com.banco.application.service.OperacoesService;

import java.util.Scanner;

public class ConsultaSaldoOption implements MenuOptions {
    private final OperacoesService operacoesService;

    public ConsultaSaldoOption(OperacoesService operacoesService) {
        this.operacoesService = operacoesService;
    }

    @Override
    public String getNomeOpcao() {
        return "Consulta de Dados da Conta";
    }


    @Override
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numero da Conta com dígito (xxxx-x): ");
        String numeroConta = scanner.next();
        try {
            System.out.println("Consultando dados...");
            Thread.sleep(2000);
            operacoesService.consultarSaldo(numeroConta);
        } catch (
                OperacoesExceptions e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
