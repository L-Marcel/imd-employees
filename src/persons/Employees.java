package src.persons;

import java.util.LinkedList;
import java.util.stream.Collectors;

import src.persons.common.Person;
import src.persons.models.AdministrativeTechnician;
import src.persons.models.Teacher;

public class Employees {
    //#region Teachers
    public static void addTeacher(Teacher teacher) {
        Persons persons = Persons.getInstance();
        persons.add(teacher);
    };

    public static void removeTeacher(Teacher teacher) {
        Persons persons = Persons.getInstance();
        persons.remove(teacher);
    };

    public static LinkedList<Teacher> getTeachers() {
        Persons persons = Persons.getInstance();
        return persons.get().stream()
            .filter((t) -> t instanceof Teacher)
            .map((t) -> (Teacher) t)
            .collect(Collectors.toCollection(LinkedList::new));
    };

    public static LinkedList<Teacher> getTeachersByDiscipline(String discipline) {
        return Employees.getTeachers()
            .stream()
            .filter((t) -> t.getDisciplines().contains(discipline))
            .collect(Collectors.toCollection(LinkedList::new));
    };

    // [QUESTION] Teachers and technicians can have same registration?
    public static Teacher getTeacherByRegistration(Long registration) {
        Persons persons = Persons.getInstance();
        for (Person person : persons.get()) {
            if (
                person instanceof Teacher && 
                person.getRegistration() == registration)
             {
                return (Teacher) person;
            };
        };

        return null;
    };
    //#endregion

    //#region Administrative Technicians
    public static void addAdministrativeTechnician(
        AdministrativeTechnician administrativeTechnician
    ) {
        Persons persons = Persons.getInstance();
        persons.add(administrativeTechnician);
    };

    public static void removeAdministrativeTechnician(
        AdministrativeTechnician administrativeTechnician
    ) {
        Persons persons = Persons.getInstance();
        persons.remove(administrativeTechnician);
    };

    public static LinkedList<AdministrativeTechnician> getAdministrativeTechnicians() {
        Persons persons = Persons.getInstance();
        return persons.get().stream()
            .filter((t) -> t instanceof AdministrativeTechnician)
            .map((t) -> (AdministrativeTechnician) t)
            .collect(Collectors.toCollection(LinkedList::new));
    };

    // [QUESTION] How to list by work function?
    public static AdministrativeTechnician getAdministrativeTechnicianByRegistration(
        Long registration
    ) {
        Persons persons = Persons.getInstance();
        for (Person person : persons.get()) {
            if (
                person instanceof AdministrativeTechnician && 
                person.getRegistration() == registration)
             {
                return (AdministrativeTechnician) person;
            };
        };

        return null;
    };
    //#endregion
};
