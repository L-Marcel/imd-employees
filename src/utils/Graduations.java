package src.utils;

import src.persons.enums.Graduation;

public class Graduations {
    private static String[] options = {
        "Especialização",
        "Mestrado",
        "Doutorado"
    };

    public static String[] getOptions() {
        return Graduations.options;
    };

    public static Graduation convert(Integer option) {
        return Graduation.fromValue(option);
    };
};
