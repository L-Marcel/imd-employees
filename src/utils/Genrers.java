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

    public static String toString(Genrer genrer) {
        int index = genrer.getValue() % 3;
        return Genrers.options[index];
    };
};
