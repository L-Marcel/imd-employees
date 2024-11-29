package src.pages;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Disciplines;
import src.persons.Employees;
import src.persons.models.Teacher;

public class TeachersPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to teachers page.");
        menu.header("Professores");

        int selectedFilter = 0;
        Disciplines disciplines = Disciplines.getInstance();
        String[] mappedDisicplines = disciplines.getArray();
        LinkedList<Teacher> teachers;
        
        if (mappedDisicplines.length > 0) {
            String[] filterOptions = {"Nome", "Disciplinas"};
            selectedFilter = menu.getOption("Listar por: ", filterOptions);
        };

        if (selectedFilter == 0) {
            teachers = Employees.getTeachers();
        } else {
            int selectedDiscpline = menu.getOption("Disciplina: ", mappedDisicplines);
            String discpline = mappedDisicplines[selectedDiscpline];
            teachers = Employees.getTeachersByDiscipline(discpline);
        }; 
        
        menu.divider();
        String[] options = teachers.stream()
            .map((t) -> t.toString())
            .toArray(String[]::new);

        if (options.length <= 0) {
            menu.push("Não há professores cadastrados!");
            menu.divider();
            menu.pushPageBack();
            router.back();
        } else {
            // [TODO] Finish it
            int selected = menu.getPageOption(options, 0, 5, 0, "Voltar");
            router.back();
        };
    };
};
