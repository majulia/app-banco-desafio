package com.banco.adapters.in.console;

import com.banco.application.exceptions.OperacoesExceptions;
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
        System.out.print("Número Conta origem com dígito(xxxx-x): ");
        String origem = scanner.next();
        System.out.print("Número Conta destino com dígito(xxxx-x): ");
        String destino = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        try {
            System.out.println("Processando a operação...");
            Thread.sleep(2000);
            operacoesService.transferir(origem, destino, valor);
        } catch (OperacoesExceptions e){
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
