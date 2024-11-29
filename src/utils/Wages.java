package src.utils;

import pretty.errors.InvalidInput;
import src.log.Log;

public class Wages {
    public static String format(Double wage) {
        return String.format("R$ %.2f", wage);
    };

    public static void validate(Double candidate, Double base) throws InvalidInput {
        if (base >= 0 && candidate < base) throw new InvalidInput("A base salarial é de " + Wages.format(base) + "!");
        else if (base < 0) Log.print("Error", "Base wage is negative!");
    };
};
