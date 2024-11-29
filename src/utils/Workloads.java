package src.utils;

import pretty.errors.InvalidInput;

public class Workloads {
    public static String format(Integer workload) {
        return workload + " horas";
    };

    public static void validate(Integer candidate) throws InvalidInput {
        if (candidate < 1d) throw new InvalidInput("A carga horária deve ser maior ou igual a 1 hora!");
    };
};
