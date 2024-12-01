package src.pages.teacher;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Disciplines;
import src.persons.Employees;
import src.persons.models.Teacher;

public class FilterTeachersPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to filter teachers page.");

        menu.header("Professores");

        int selectedFilter = 0;
        Disciplines disciplines = Disciplines.getInstance();
        String[] mappedDisicplines = disciplines.getArray();
        String[] filterOptions = {"Nome", "Disciplinas"};
        LinkedList<Teacher> teachers;
        
        if (mappedDisicplines.length > 0) {
            selectedFilter = menu.getOption("Listar por: ", filterOptions);
        };

        if (selectedFilter == 0) {
            teachers = Employees.getTeachers();
        } else {
            int selectedDiscpline = menu.getOption("Disciplina: ", mappedDisicplines);
            String discpline = mappedDisicplines[selectedDiscpline];
            teachers = Employees.getTeachersByDiscipline(discpline);
        };

        router.replace(new TeachersPage(teachers, "Listando por: " + filterOptions[selectedFilter]));
    };
};
