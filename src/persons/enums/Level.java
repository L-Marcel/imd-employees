package src.persons.enums;

public enum Level {
    I(0),
    II(1),
    III(2),
    IV(3),
    V(4),
    VI(5),
    VII(6),
    VIII(7);

    private Integer value = 0;
    
    private Level(Integer value) {
        this.value = value;
    };

    public Integer getValue() {
        return this.value;
    };

    public Level fromValue(Integer value) {
        for (Level level : Level.values()) {
            if (level.getValue() == value) {
                return level;
            };
        };

        return Level.I;
    };
};
