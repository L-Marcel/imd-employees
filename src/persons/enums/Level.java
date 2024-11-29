package src.persons.enums;

public enum Level {
    I((short) 0),
    II((short) 1),
    III((short) 2),
    IV((short) 3),
    V((short) 4),
    VI((short) 5),
    VII((short) 6),
    VIII((short) 7);

    private Short value = 0;
    
    private Level(Short value) {
        this.value = value;
    };

    public Short getValue() {
        return this.value;
    };

    public static Level fromValue(Integer value) {
        for (Level level : Level.values()) {
            if (Short.toUnsignedInt(level.getValue()) == value) {
                return level;
            };
        };

        return Level.I;
    };
};
