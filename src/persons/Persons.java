package src.persons;

import src.core.Storable;

public class Persons extends Storable<Person> {
    private static Persons instance;

    private Persons() {
        super("persons");
    };

    public static Persons getInstance() {
        if (Persons.instance == null) Persons.instance = new Persons();
        return Persons.instance;
    };
};
