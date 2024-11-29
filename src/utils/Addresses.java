package src.utils;

import pretty.errors.InvalidInput;

public class Addresses {
    public static void validateStreet(String candidate) throws InvalidInput {
        if (candidate.length() < 3) throw new InvalidInput("A rua deve ter pelo menos 3 caracteres!");
        else if (candidate.length() > 85) throw new InvalidInput("A rua de ver no máximo 85 caracteres!");
    };

    public static void validateCity(String candidate) throws InvalidInput {
        if (candidate.length() < 3) throw new InvalidInput("A cidade deve ter pelo menos 3 caracteres!");
        else if (candidate.length() > 85) throw new InvalidInput("A cidade de ver no máximo 85 caracteres!");
    };

    public static void validateDistrict(String candidate) throws InvalidInput {
        if (candidate.length() < 3) throw new InvalidInput("O bairro deve ter pelo menos 3 caracteres!");
        else if (candidate.length() > 85) throw new InvalidInput("O bairro de ver no máximo 85 caracteres!");
    };

    public static void validateNumber(Integer candidate) throws InvalidInput {
        if (candidate < 0) throw new InvalidInput("O número deve ser maior ou igual a 0!");
        else if (candidate > 10000) throw new InvalidInput("O número deve ser menor ou igual a 10000!");
    };
};
