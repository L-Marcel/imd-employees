package src.persons;

import pretty.errors.InvalidInput;
import src.storage.StorableMap;

public class Disciplines extends StorableMap<String, Integer> {
    private static Disciplines instance;

    private Disciplines() {
        super("disciplines");
    };

    public static Disciplines getInstance() {
        if (Disciplines.instance == null) Disciplines.instance = new Disciplines();
        return Disciplines.instance;
    };

    /**
     * Increment the number of teachers linked to a discipline
     * @param discipline - the discipline
     */
    public void increment(String discipline) {
        Integer count = this.get().getOrDefault(discipline, 0);
        this.get().put(discipline, count + 1);
    };

    /**
     * Decerement the number of teachers linked to a discipline
     * @param discipline - the discipline
     */
    public void decrement(String discipline) {
        Integer count = this.get().getOrDefault(discipline, 0);
        if (count > 0) {
            this.get().put(discipline, count - 1);
        } else {
            this.get().remove(discipline);
        };
    };

    /**
     * Increment the number of teachers linked to many disciplines
     * @param disciplines - the disciplines
     */
    public void increment(String[] disciplines) {
        for (String discipline : disciplines) {
            this.increment(discipline);
        };

        this.store();
    };

    /**
     * Decrement the number of teachers linked to many disciplines
     * @param disciplines - the disciplines
     */
    public void decrement(String[] disciplines) {
        for (String discipline : disciplines) {
            this.decrement(discipline);
        };

        this.store();
    };

    public void validate(String candidate) throws InvalidInput {
        int result = this.get().getOrDefault(candidate, 0);
        if (result > 0) throw new InvalidInput("Essa disciplina já existe!");
    };

    /**
     * Get all discplines as an array
     * @return disciplines array
     */
    public String[] getArray() {
        Disciplines disciplines = Disciplines.getInstance();
        return disciplines.get()
            .sequencedKeySet()
            .toArray(String[]::new);
    };
};
