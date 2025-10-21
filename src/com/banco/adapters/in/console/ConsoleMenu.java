package com.banco.adapters.in.console;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final List<MenuOptions> opcoes;

    public ConsoleMenu(List<MenuOptions> opcoes) {
        this.opcoes = opcoes;
    }

    public void exibir(){
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n === Menu Principal ===");
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.printf("%d - %s%n", i + 1, opcoes.get(i).getNomeOpcao());
            }
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");
            escolha = scanner.nextInt();

            if (escolha > 0 && escolha <= opcoes.size()){
                opcoes.get(escolha - 1).executar();
            }
        } while (escolha != 0);

        System.out.println("Saindo do sistema");
    }
}
