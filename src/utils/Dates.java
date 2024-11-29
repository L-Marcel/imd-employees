package src.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import pretty.errors.InvalidInput;

public class Dates {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        .withLocale(Locale.forLanguageTag("pt_BR"));
    
    public static String format(LocalDate date) {
        if (date == null) return "";
        return formatter.format(date);
    };
    
    public static LocalDate fromString(String date) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    };

    public static void validate(String candidate) throws InvalidInput {
        LocalDate date = fromString(candidate);
        if (date == null) throw new InvalidInput("A data deve seguir o formato dd/mm/yyyy!");
    };
};
