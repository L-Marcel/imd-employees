package src.persons.models;

import java.time.LocalDate;
import java.util.ArrayList;

import src.persons.common.Address;
import src.persons.common.Employee;
import src.persons.common.Person;
import src.persons.enums.Genrer;
import src.persons.enums.Graduation;
import src.persons.enums.Level;

public class Teacher extends Person implements Employee {
    private Level level;
    private Graduation graduation;
    private ArrayList<String> disciplines;

    public Teacher(
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
        Graduation graduation,
        ArrayList<String> disciplines
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
        this.graduation = graduation;
        this.disciplines = disciplines;
    };

    @Override
    public Double getWageWithBonus() {
        // [TODO] Sum or multiply bonus?
        return (
            1.05d + 
            (0.05d * this.level.getValue()) +
            1.25d +
            (0.25d * this.graduation.getValue())
        ) * this.getWage();
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

    public ArrayList<String> getDisciplines() {
        return disciplines;
    };

    public void setDisciplines(ArrayList<String> disciplines) {
        this.disciplines = disciplines;
    };
};
