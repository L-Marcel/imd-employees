package src.persons;

import src.core.StorableList;
import src.persons.common.Person;
import src.persons.models.Teacher;

public class Persons extends StorableList<Person> {
    private static Persons instance;

    private Persons() {
        super("persons");
    };

    public static Persons getInstance() {
        if (Persons.instance == null) Persons.instance = new Persons();
        return Persons.instance;
    };

    @Override
    public void remove(Person t) {
        this.get().remove(t);

        if (t instanceof Teacher) {
            Disciplines map = Disciplines.getInstance();
            Teacher teacher = (Teacher) t;
            map.decrement(teacher.getDisciplines().toArray(String[]::new));
        };

        store();
    };
};
