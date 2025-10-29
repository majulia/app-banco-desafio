package com.banco.adapters.in.console;

import com.banco.adapters.in.console.util.InputUtils;
import com.banco.application.service.OperacoesService;
import com.banco.domain.model.TipoConta;

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

        System.out.println("Selecione o tipo da conta:");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        System.out.println("3 - Salário");
        System.out.println("Entre com a opção desejada:");
        int opcaoTipoConta = scanner.nextInt();
        TipoConta tipoConta;

        switch (opcaoTipoConta){
            case 1 -> tipoConta = TipoConta.CORRENTE;
            case 2 -> tipoConta = TipoConta.POUPANCA;
            case 3 -> tipoConta = TipoConta.SALARIO;
            default -> {
                System.out.println("Opção Inválida, tipo padrão Corrente aplicado");
                tipoConta = TipoConta.CORRENTE;
            }
        }
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.next();
        System.out.println("Saldo Inicial:");
        double saldoInicial = scanner.nextDouble();
        LocalDate dataNascimento = InputUtils.lerData("Data de nascimento (dd/MM/yyyy):");

        operacoesService.criarConta(nome, cpf, telefone, dataNascimento, saldoInicial, tipoConta);
    }


}
