package src.utils;

import pretty.errors.InvalidInput;

public class Departaments {
    public static void validate(String candidate) throws InvalidInput {
        if (candidate.length() < 3) throw new InvalidInput("O departamento deve ter pelo menos 3 caracteres!");
        else if (candidate.length() > 85) throw new InvalidInput("O departamento de ver no máximo 85 caracteres!");
    };
};
