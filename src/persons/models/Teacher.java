package src.persons.models;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.ArrayList;

import src.persons.Disciplines;
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

    public Teacher() {};
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
        Disciplines map = Disciplines.getInstance();
        map.increment(this.disciplines.toArray(String[]::new));
    };

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeShort(this.level.getValue());
        out.writeShort(this.graduation.getValue());
        out.writeObject(this.disciplines);
    };

    
    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        this.level = Level.fromValue(Short.toUnsignedInt(in.readShort()));
        this.graduation = Graduation.fromValue(Short.toUnsignedInt(in.readShort()));
        this.disciplines = (ArrayList<String>) in.readObject();
        in.close();
    };

    @Override
    public Double getLevelBonus() {
        return (Math.pow(1.05d, this.level.getValue()) - 1) * this.getWage();
    };

    @Override
    public Double getGraduationBonus() {
        return (0.25d + (0.25d * this.graduation.getValue())) * this.getWage();
    };

    @Override
    public Double getWageWithBonus() {
        return this.getWage() + 
            this.getLevelBonus() + 
            this.getGraduationBonus();
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
        Disciplines map = Disciplines.getInstance();
        map.decrement(this.disciplines.toArray(String[]::new));
        this.disciplines = disciplines;
        map.increment(this.disciplines.toArray(String[]::new));
    };
};
