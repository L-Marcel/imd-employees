package src.persons;

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

    public void increment(String[] disciplines) {
        for (String discipline : disciplines) {
            Integer count = this.get().getOrDefault(discipline, 0);
            this.get().put(discipline, count + 1);
        };

        this.store();
    };

    public void decrement(String[] disciplines) {
        for (String discipline : disciplines) {
            Integer count = this.get().getOrDefault(discipline, 0);
            if (count > 0) {
                this.get().put(discipline, count - 1);
            } else {
                this.get().remove(discipline);
            };
        };

        this.store();
    };
};
