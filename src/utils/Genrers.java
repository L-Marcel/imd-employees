package src.utils;

import src.persons.enums.Genrer;

public class Genrers {
    private static String[] options = {
        "Homem",
        "Mulher",
        "Outro"
    };

    public static String[] getOptions() {
        return Genrers.options;
    };

    public static Genrer convert(Integer option) {
        return Genrer.fromValue(option);
    };
};
