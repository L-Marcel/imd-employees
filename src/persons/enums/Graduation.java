package src.persons.enums;

public enum Graduation {
    SPECIALIZED((short) 0),
    MASTER((short) 1),
    DOCTOR((short) 2);

    private Short value;

    private Graduation(Short value) {
        this.value = value;
    };

    public Short getValue() {
        return this.value;
    };

    public static Graduation fromValue(Integer value) {
        for (Graduation graduation : Graduation.values()) {
            if (Short.toUnsignedInt(graduation.getValue()) == value) {
                return graduation;
            };
        };

        return Graduation.SPECIALIZED;
    };
};
