package src.persons;

import java.util.LinkedHashMap;

import pretty.errors.InvalidInput;
import src.core.StorableMap;

public class Disciplines extends StorableMap<String, Integer> {
    private static Disciplines instance;

    private Disciplines() {
        super("disciplines");
    };

    public static Disciplines getInstance() {
        if (Disciplines.instance == null) Disciplines.instance = new Disciplines();
        return Disciplines.instance;
    };

    public void increment(String discipline) {
        Integer count = this.get().getOrDefault(discipline, 0);
        this.get().put(discipline, count + 1);
    };

    public void decrement(String discipline) {
        Integer count = this.get().getOrDefault(discipline, 0);
        if (count > 0) {
            this.get().put(discipline, count - 1);
        } else {
            this.get().remove(discipline);
        };
    };

    public void increment(String[] disciplines) {
        for (String discipline : disciplines) {
            this.increment(discipline);
        };

        this.store();
    };

    public void decrement(String[] disciplines) {
        for (String discipline : disciplines) {
            this.decrement(discipline);
        };

        this.store();
    };

    public void validate(String discipline) throws InvalidInput {
        int result = this.get().getOrDefault(discipline, 0);
        if (result > 0) throw new InvalidInput("Essa disciplina já existe!");
    };

    public String[] getArray() {
        Disciplines disciplines = Disciplines.getInstance();
        return disciplines.get()
            .sequencedKeySet()
            .toArray(String[]::new);
    };
};
