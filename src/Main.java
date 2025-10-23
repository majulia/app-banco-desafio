import com.banco.adapters.in.console.*;
import com.banco.application.service.OperacoesService;
import com.banco.infra.export.ExporterCsv;
import com.banco.infra.persistence.ContaRepositoryInMemory;

void main() {
    var repo = new ContaRepositoryInMemory();
    var exporter = new ExporterCsv();
    var operacoesService = new OperacoesService(repo, exporter);

    ConsoleMenu menu;
    menu = new ConsoleMenu(List.of(
            new CriarContaOption(operacoesService),
            new ConsultaSaldoOption(operacoesService),
            new DepositarOption(operacoesService),
            new SacarOption(operacoesService),
            new TransferirOption(operacoesService),
            new ExportarHistoricoOption(operacoesService)
    ));

    menu.exibir();
}
