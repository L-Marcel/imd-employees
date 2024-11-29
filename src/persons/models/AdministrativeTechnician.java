package src.persons.models;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

import src.persons.common.Address;
import src.persons.common.Employee;
import src.persons.common.Person;
import src.persons.enums.Genrer;
import src.persons.enums.Graduation;
import src.persons.enums.Level;

public class AdministrativeTechnician extends Person implements Employee {
    private Level level;
    private Graduation graduation;
    private Boolean unhealthiness;
    private Boolean gratification;

    public AdministrativeTechnician() {};
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
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeShort(this.level.getValue());
        out.writeShort(this.graduation.getValue());
        out.writeBoolean(this.unhealthiness);
        out.writeBoolean(this.gratification);
    };

    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        this.level = Level.fromValue(Short.toUnsignedInt(in.readShort()));
        this.graduation = Graduation.fromValue(Short.toUnsignedInt(in.readShort()));
        this.unhealthiness = in.readBoolean();
        this.gratification = in.readBoolean();
        in.close();
    };

    public Double getUnhealthinessBonus() {
        if (this.unhealthiness) return 0.5d * this.getWage();
        return 0d;
    };

    public Double getGratificationBonus() {
        if (this.gratification) return 0.5d * this.getWage();
        return 0d;
    };

    @Override
    public Double getLevelBonus() {
        return (Math.pow(1.03d, this.level.getValue()) - 1) * this.getWage();
    };

    @Override
    public Double getGraduationBonus() {
        return (0.25d + (0.25d * this.graduation.getValue())) * this.getWage();
    };

    @Override
    public Double getWageWithBonus() {
        return this.getWage() + 
            this.getLevelBonus() + 
            this.getGraduationBonus() + 
            this.getGraduationBonus() + 
            this.getUnhealthinessBonus();
    };

    public Level getLevel() {
        return level;
    };

    public void setLevel(Level level) {
        this.level = level;
    };

    public Graduation getGraduation() {
        return graduation;
    };

    public void setGraduation(Graduation graduation) {
        this.graduation = graduation;
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
