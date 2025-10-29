# Desafio Aplicação Bancária
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=%20Finalizado&color=GREEN&style=for-the-badge)

<p align="center">
<a href="#sobre">Desafio </a> •
<a href="#sobre">Pré Requisitos</a> • 
<a href="#sobre">Observações / Instruções </a>  • 
<a href="#sobre"> Diagrama </a>

## Desafio

Solicitação
Faça uma aplicação bancária que permita transferências de valores. O sistema deve conter as seguintes funcionalidades (mínimas):
-	Cadastro de conta bancária
-	Numero da conta, numero agencia, cliente, saldo, limite, tipo da conta
-	Depósito
-	Retirada (saque)
-	Alteração de limite
-	Transferências
--	Pensem em limitar o valor de acordo com o horário;
  - Exportação de histórico de transações (CSV).
 
A aplicação deve conter um menu via terminal para seleção da operação desejada.
Considerações:
-	Perceba que a descrição do sistema foi feita de forma genérica, propositalmente para encorajar a extensão de funcionalidades de acordo com a sua necessidade.
-	As funcionalidades descritas acima são básicas e mandatórias para o funcionamento e aceite da entrega.
-	Utilize o máximo de conceitos abordados durante o curso. Ex: menus com Scanner, boas práticas de nomenclatura, herança, listas, interfaces, trabalho com arquivos, etc.
-	Não há necessidade de persistência em bancos de dados. Pensem numa estrutura utilizando listas/mapas em memória para armazenamento.
-	Os relacionamentos entre as classes (entidades do sistema) ficam ao seu critério. Utilizem quantas classes e atributos julgarem necessário para a modelagem.
-	Sigam o princípio: baixo acoplamento, alta coesão.
-	Para estruturar seu código, imagine a aplicação como um entregável que possa ser evoluído sem a necessidade de grande refatoração. Ex: não tenho um banco de dados hoje ou uma API Rest para acesso às operações, mas posso construir um módulo sem afetar O DOMÍNIO do sistema (classes de negócio e entidades).
-	Pensem que toda operação repetitiva pode ter sua própria classe ou método, como apresentação das informações na tela (ou input), que pode ter dados como parâmetros.

## Pré Requisitos

- IDE compatível Java (Usado para esse projeto IntelliJ 2025.2.3 (Community Edition))
- Java OpenJDK 25

## Observações / Instruções
:warning: Para executar a aplicação, vá até o arquivo **Main.java**.  Assim que a aplicação iniciar, **é obrigatório usar a opção "1 - Criar conta" do menu principal** para testar outras funcionalidades simuladas. 

Além dos requisitos mandatórios, tentei usar esse desafio como treinamento para a arquitetura hexagonal mesmo não sendo uma API. *(Futuramente será refatorado quando tiver o domínio desse conhecimento :bowtie:)*

## Diagrama de Classes
```mermaid
  classDiagram
    %% ===== Domínio =====
    class Cliente {
        -String nome
        -String cpf
        -String telefone
        -LocalDate dataNascimento
    }

    class Transacao {
        -String tipo
        -double valor
        -LocalDate data
        -LocalTime hora
    }

    class Conta {
        -String agencia
        -String numeroDaConta
        -TipoConta tipoConta
        -Cliente cliente
        -double saldo
        -double limite
        -List~Transacao~ historico
        +Conta(double limite, double saldo, Cliente cliente, String agencia, String numeroDaConta, TipoConta tipoConta)
        +depositar(double)
        +sacar(double)
        +transferir(Conta destino, double valor)
        +getNumeroDaConta() String
        +getSaldo() double
        +getHistorico() List~Transacao~
        +setLimite(double)
        +getTipoConta() TipoConta
        +toString() String
    }
    class TipoConta {
        <<enum>>
        CORRENTE
        POUPANCA
        SALARIO
    }
    %% Relacionamentos de domínio
    Conta --> Cliente : possui >
    Conta "1" -- "*" Transacao : registra >
    Conta --> TipoConta : classifica >
    %% ===== Application / Casos de Uso =====
    class OperacoesRepository {
        <<interface>>
        +criarConta(...)
        +consultarSaldo(String)
        +depositar(String, double)
        +sacar(String, double)
        +transferir(String, String, double)
        +alterarLimite(String, double)
        +exportarExtrato(String, String)
    }

    class OperacoesService {
        -ContaRepository contaRepository
        -ExporterCsv exporterCsv
        +criarConta(...)
        +consultarSaldo(String)
        +depositar(String, double)
        +sacar(String, double)
        +transferir(String, String, double)
        +alterarLimite(String, double)
        +exportarExtrato(String, String)
    }

    OperacoesRepository <|.. OperacoesService : implementa

    %% ===== Infraestrutura =====
    class ContaRepository {
        <<interface>>
        +salvar(Conta)
        +buscarContaPorNumero(String)
    }

    class ContaRepositoryInMemory {
        -Map~String, Conta~ contas
        +salvar(Conta)
        +buscarContaPorNumero(String)
    }

    ContaRepository <|.. ContaRepositoryInMemory : implementa

    class ExporterCsv {
        +exportar(List~Transacao~, String)
    }

    %% Serviços usando infra
    OperacoesService --> ContaRepository : usa
    OperacoesService --> ExporterCsv : usa

```


