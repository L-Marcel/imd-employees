package src.persons.models;

import java.util.ArrayList;

import src.persons.common.Person;
import src.persons.enums.Graduation;
import src.persons.enums.Level;

public class Teacher extends Person {
    private Level level;
    private Graduation graduation;
    private ArrayList<String> disciplines;

    @Override
    public Double getWage() {
        // [TODO] Finish it
        return this.wage;
    };
};
