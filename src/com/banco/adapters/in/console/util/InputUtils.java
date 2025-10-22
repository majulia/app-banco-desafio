package com.banco.adapters.in.console.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {
                return LocalDate.parse(entrada, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida! Use o formato dd/MM/yyyy");
            }
        }
    }
}