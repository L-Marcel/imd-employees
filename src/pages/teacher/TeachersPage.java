package src.pages.teacher;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Disciplines;
import src.persons.Employees;
import src.persons.models.Teacher;

public class TeachersPage implements Page {
    private LinkedList<Teacher> teachers;
    private String filterMessage;

    public TeachersPage(LinkedList<Teacher> teachers, String filterMessage) {
        this.teachers = teachers;
        this.filterMessage = filterMessage; 
    };

    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to teachers page.");

        menu.header("Professores");
        menu.push(filterMessage);
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
            int selected = menu.getPageOption(options, 0, 5, 0, "Voltar");
            if (selected < 0) {
                router.back();
            } else {
                Teacher teacher = teachers.get(selected);
                router.navigate(new TeacherPage(teacher));
            };
        };
    };
};
