package src.utils;

import src.persons.enums.Level;

public class Levels {
    private static String[] options = {
        "I",
        "II",
        "III",
        "IV",
        "V",
        "VI",
        "VII",
        "VIII"
    };

    public static String[] getOptions() {
        return Levels.options;
    };

    public static Level convert(Integer option) {
        return Level.fromValue(option);
    };

    public static String toString(Level level) {
        int index = level.getValue() % 8;
        return Levels.options[index];
    };
};
