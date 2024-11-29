package src.persons.enums;

public enum Genrer {
    MALE((short) 0),
    FEMALE((short) 1),
    OTHER((short) 2);

    private Short value;

    private Genrer(Short value) {
        this.value = value;
    };

    public Short getValue() {
        return this.value;
    };

    public static Genrer fromValue(Integer value) {
        for (Genrer genrer : Genrer.values()) {
            if (Short.toUnsignedInt(genrer.getValue()) == value) {
                return genrer;
            };
        };

        return Genrer.OTHER;
    };
};
