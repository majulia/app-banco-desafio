package com.banco.infra.export;

import com.banco.domain.model.Transacao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExporterCsv {

    public void exportar(List<Transacao> transacoes, String caminhoArquivo) {
        try (PrintWriter writer = new PrintWriter(new File(caminhoArquivo))) {
            writer.println("===== Extrato Bancário ====");
            writer.println("Tipo de operação - Valor - Data");
            for (Transacao t : transacoes) {
                writer.printf("%s - %.2f - %s%n", t.getTipo(), t.getValor(), t.getData());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar CSV - ", e);
        }
    }
}
