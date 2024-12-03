package src.persons;

import java.util.LinkedList;
import java.util.stream.Collectors;

import src.persons.common.Person;
import src.persons.models.AdministrativeTechnician;
import src.persons.models.Teacher;

public class Employees {
    //#region Teachers
    /**
     * Add an teacher to the system
     * @param teacher - the teacher
     */
    public static void addTeacher(Teacher teacher) {
        Persons persons = Persons.getInstance();
        persons.add(teacher);
    };

    /**
     * Remove an teacher to the system
     * @param teacher - the teacher
     */
    public static void removeTeacher(Teacher teacher) {
        Persons persons = Persons.getInstance();
        persons.remove(teacher);
    };    /**
    * Get a teacher by registration
    * @param registration - the registration
    * @return the teacher
    */

    /**
     * Get all teachers
     * @return teachers
     */
    public static LinkedList<Teacher> getTeachers() {
        Persons persons = Persons.getInstance();
        return persons.get().stream()
            .filter((t) -> t instanceof Teacher)
            .map((t) -> (Teacher) t)
            .collect(Collectors.toCollection(LinkedList::new));
    };

    /**
     * Get teachers by discipline
     * @param discipline - the discipline
     * @return the teachers
     */
    public static LinkedList<Teacher> getTeachersByDiscipline(String discipline) {
        return Employees.getTeachers()
            .stream()
            .filter((t) -> t.getDisciplines().contains(discipline))
            .collect(Collectors.toCollection(LinkedList::new));
    };

    /**
     * Get a teacher by registration
     * @param registration - the registration
     * @return the teacher
     */
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
    /**
     * Add an administrative technician to the system
     * @param administrativeTechnician - the administrative technician
     */
    public static void addAdministrativeTechnician(
        AdministrativeTechnician administrativeTechnician
    ) {
        Persons persons = Persons.getInstance();
        persons.add(administrativeTechnician);
    };

    /** 
     * Remove an administrative technician to the system
     * @param administrativeTechnician - the administrative technician
    */
    public static void removeAdministrativeTechnician(
        AdministrativeTechnician administrativeTechnician
    ) {
        Persons persons = Persons.getInstance();
        persons.remove(administrativeTechnician);
    };

    /**
     * Get all administrative technicians
     * @return administrative technicians
     */
    public static LinkedList<AdministrativeTechnician> getAdministrativeTechnicians() {
        Persons persons = Persons.getInstance();
        return persons.get().stream()
            .filter((t) -> t instanceof AdministrativeTechnician)
            .map((t) -> (AdministrativeTechnician) t)
            .collect(Collectors.toCollection(LinkedList::new));
    };

    /**
     * Get an administrative technician by registration
     * @param registration - the registration
     * @return the administrative technician
     */
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
