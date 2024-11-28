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

    // public Double applyBonus(Double wage) {
    //     return (1.05d + (0.05d * this.value)) * wage;
    // };

    public Level fromValue(Integer value) {
        for (Level level : Level.values()) {
            if (level.getValue() == value) {
                return level;
            };
        };

        return Level.I;
    };
};
