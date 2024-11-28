package src.persons.models;

import java.time.LocalDate;

import src.persons.common.Address;
import src.persons.common.Employee;
import src.persons.common.Person;
import src.persons.enums.Genrer;
import src.persons.enums.Level;

public class AdministrativeTechnician extends Person implements Employee {
    private Level level;
    private Boolean unhealthiness;
    private Boolean gratification;

    public AdministrativeTechnician(
        String name, 
        String cpf, 
        LocalDate birthdate, 
        Genrer genrer, 
        Address address,
        Long registration, 
        Double wage, 
        String department, 
        Integer workload, 
        LocalDate joinedAt, 
        Level level,
        Boolean unhealthiness, 
        Boolean gratification
    ) {
        super(
            name, 
            cpf, 
            birthdate, 
            genrer, 
            address, 
            registration, 
            wage, 
            department, 
            workload, 
            joinedAt
        );
        this.level = level;
        this.unhealthiness = unhealthiness;
        this.gratification = gratification;
    };

    @Override
    public Double getWageWithBonus() {
        return 0d;
        // [TODO] Finish it
        // [TODO] Sum or multiply bonus?
        // return (
        //     1.03d + 
        //     (0.03d * this.level.getValue()) +
        //     1.25d +
        //     (0.25d * this.graduation.getValue()) +
        //     (this.unhealthiness? 0.50d:0d)
        // ) * this.getWage();
    };

    public Level getLevel() {
        return level;
    };

    public void setLevel(Level level) {
        this.level = level;
    };

    public Boolean getUnhealthiness() {
        return unhealthiness;
    };

    public void setUnhealthiness(Boolean unhealthiness) {
        this.unhealthiness = unhealthiness;
    };

    public Boolean getGratification() {
        return gratification;
    };

    public void setGratification(Boolean gratification) {
        this.gratification = gratification;
    };
};
