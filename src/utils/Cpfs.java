package src.utils;

import java.util.regex.Pattern;

import pretty.errors.InvalidInput;
import src.persons.Persons;
import src.persons.common.Person;

public class Cpfs {
    private static Pattern first_pattern = Pattern.compile("^((\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2}))$");
    private static Pattern second_pattern = Pattern.compile("^\\d{11}$");
    
    public static String format(String cpf) {
        if (Cpfs.second_pattern.matcher(cpf).find()) {
            return String.format(
                "%s.%s.%s-%s",
                cpf.subSequence(0, 3),
                cpf.subSequence(3, 6),
                cpf.subSequence(6, 9),
                cpf.subSequence(9, 11)
            );
        } else return cpf;
    };

    public static void validate(String candidate) throws InvalidInput {
        String formattedCandidate = Cpfs.format(candidate);
        boolean formatIsValid = Cpfs.first_pattern.matcher(formattedCandidate).find();
        if (!formatIsValid) throw new InvalidInput("O CPF deve seguir o formato 000.000.000-00 ou 00000000000!");
        Persons persons = Persons.getInstance();
        for (Person person : persons.get()) {
            if (person.getCpf() == formattedCandidate) {
                throw new InvalidInput("O CPF " + formattedCandidate + " já está em uso!");
            };
        };
    };
};
