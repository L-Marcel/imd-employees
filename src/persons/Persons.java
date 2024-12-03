package src.persons;

import src.persons.common.Person;
import src.persons.models.Teacher;
import src.storage.StorableList;

public class Persons extends StorableList<Person> {
    private static Persons instance;

    private Persons() {
        super("persons");
    };

    public static Persons getInstance() {
        if (Persons.instance == null) Persons.instance = new Persons();
        return Persons.instance;
    };

    /**
     * Remove an person from the list, update teacher disciplines and request storage.
     * @param person - the person
     */
    @Override
    public void remove(Person person) {
        this.get().remove(person);

        if (person instanceof Teacher) {
            Disciplines map = Disciplines.getInstance();
            Teacher teacher = (Teacher) person;
            map.decrement(teacher.getDisciplines().toArray(String[]::new));
        };

        store();
    };
};
