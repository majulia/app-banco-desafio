import com.banco.application.service.OperacoesService;

void main() {
    OperacoesService service = new OperacoesService(repo, exporter); //criar
    Scanner sc = new Scanner(System.in);
    int opcao;

    do {
        System.out.println("=== BancoApp ===");
        System.out.println("1. Criar Conta");
        System.out.println("2. Depositar");
        System.out.println("3. Sacar");
        System.out.println("4. Transferir");
        System.out.println("5. Exportar Histórico CSV");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();

        try {
            switch (opcao) {
                case 1 -> {
                    System.out.println("Conta criada com sucesso!");
                }
                case 2 -> {
                    System.out.println("Depósito realizado!");
                }
                case 3 -> {
                    System.out.println("Saque realizado!");
                }
                case 4 -> {
                    System.out.println("Transferência feita!");
                }
                case 5 -> {

                    System.out.println("Histórico exportado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } while (opcao != 0);

    System.out.println("Encerrando o sistema!");

}
