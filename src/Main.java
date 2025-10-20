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
                    System.out.print("Número da conta: ");
                    String num = sc.next();
                    System.out.print("Nome do cliente: ");
                    String nome = sc.next();
                    System.out.print("Limite inicial: ");
                    double limite = sc.nextDouble();
                    service.criarConta(num, nome, limite);
                    System.out.println("Conta criada com sucesso!");
                }
                case 2 -> {
                    System.out.print("Conta: ");
                    String num = sc.next();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    service.depositar(num, valor);
                    System.out.println("Depósito realizado!");
                }
                case 3 -> {
                    System.out.print("Conta: ");
                    String num = sc.next();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    service.sacar(num, valor);
                    System.out.println("Saque realizado!");
                }
                case 4 -> {
                    System.out.print("Conta origem: ");
                    String origem = sc.next();
                    System.out.print("Conta destino: ");
                    String destino = sc.next();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    service.transferir(origem, destino, valor);
                    System.out.println("Transferência feita!");
                }
                case 5 -> {
                    System.out.print("Conta: ");
                    String num = sc.next();
                    System.out.print("Arquivo CSV (ex: historico.csv): ");
                    String arq = sc.next();
                    service.exportarTransacoes(num, arq);
                    System.out.println("Histórico exportado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } while (opcao != 0);

    System.out.println("Encerrando o sistema!");

}
