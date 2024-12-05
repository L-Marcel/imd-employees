package src.utils;

import pretty.errors.InvalidInput;
import src.persons.Persons;
import src.persons.common.Person;

public class Registrations {
    public static void validate(Long candidate) throws InvalidInput {
        if (candidate < 0) throw new InvalidInput("A matrícula deve ser maior ou igual a 0!");
        Persons persons = Persons.getInstance();
        for (Person person : persons.get()) {
            if (person.getRegistration().equals(candidate)) {
                throw new InvalidInput("A matrícula " + candidate + " já está em uso!");
            };
        };
    };
};
