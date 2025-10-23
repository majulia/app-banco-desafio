package com.banco.adapters.in.console;

import com.banco.adapters.in.console.util.InputUtils;
import com.banco.application.service.OperacoesService;
import static com.banco.adapters.in.console.util.InputUtils.lerData;

import java.time.LocalDate;
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

        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.next();
        System.out.println("Saldo Inicial:");
        double saldoInicial = scanner.nextDouble();
        LocalDate dataNascimento = InputUtils.lerData("Data de nascimento (dd/MM/yyyy):");

        operacoesService.criarConta(nome, cpf, telefone, dataNascimento, saldoInicial);
    }


}
