package src.utils;

import java.util.regex.Pattern;

import pretty.errors.InvalidInput;

public class Ceps {
    private static Pattern first_pattern = Pattern.compile("^((\\d{5})\\-(\\d{3}))$");
    private static Pattern second_pattern = Pattern.compile("^\\d{8}$");
    
    public static String format(String cep) {
        if (Ceps.second_pattern.matcher(cep).find()) {
            return String.format(
                "%s-%s",
                cep.subSequence(0, 5),
                cep.subSequence(5, 8)
            );
        } else return cep;
    };

    public static void validate(String candidate) throws InvalidInput {
        String formattedCandidate = Ceps.format(candidate);
        boolean formatIsValid = Ceps.first_pattern.matcher(formattedCandidate).find();
        if (!formatIsValid) throw new InvalidInput("O CEP deve seguir o formato 00000-000 ou 00000000!");
    };
};
