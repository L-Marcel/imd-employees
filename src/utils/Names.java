package src.utils;

import pretty.errors.InvalidInput;

public class Names {
    public static void validate(String candidate) throws InvalidInput {
        if (candidate.length() < 3) throw new InvalidInput("O nome deve ter pelo menos 3 caracteres!");
        else if (candidate.length() > 85) throw new InvalidInput("O nome de ver no máximo 85 caracteres!");
    };
};
