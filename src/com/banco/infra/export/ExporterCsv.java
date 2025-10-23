package com.banco.infra.export;

import com.banco.domain.model.Transacao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExporterCsv {

    public void exportar(List<Transacao> transacoes, String caminhoArquivo) {
        try (PrintWriter writer = new PrintWriter(new File(caminhoArquivo))) {
            writer.println("===== Extrato Bancário ====");
            writer.println("Tipo de operação - Valor - Data - Hora");
            for (Transacao t : transacoes) {
                LocalTime horaTransacao = t.getHora();
                DateTimeFormatter formatoHoraData = DateTimeFormatter.ofPattern("HH:mm:ss");

                String horaFormatada = horaTransacao.format(formatoHoraData);
                writer.printf("%s - %.2f - %s - %s%n ", t.getTipo(), t.getValor(), t.getData(), horaFormatada);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar CSV - ", e);
        }
    }

}
