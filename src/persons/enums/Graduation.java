package src.persons.enums;

public enum Graduation {
    SPECIALIZED(0),
    MASTER(1),
    DOCTOR(2);

    private Integer value;

    private Graduation(Integer value) {
        this.value = value;
    };

    public Integer getValue() {
        return this.value;
    };

    // public Double applyBonus(Double wage) {
    //     return (1.25d + (0.25d * this.value)) * wage;
    // };

    public Graduation fromValue(Integer value) {
        for (Graduation graduation : Graduation.values()) {
            if (graduation.getValue() == value) {
                return graduation;
            };
        };

        return Graduation.SPECIALIZED;
    };
};
