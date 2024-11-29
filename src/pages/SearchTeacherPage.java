package src.pages;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.models.Teacher;
import src.utils.Registrations;

public class SearchTeacherPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to search teacher page.");
        menu.header("Buscando Professor");

        Long registration = menu.getLong(
            "Matrícula: ", 
            (t) -> Registrations.validate(t)
        );

        menu.divider();

        Teacher teacher = Employees.getTeacherByRegistration(registration);

        if (teacher == null) {
            menu.push("Nenhum professor encontrado!");
            menu.divider();
            menu.pushPageBack();
            router.back();
        } else {
            // [TODO] Finish it
            router.back();
        };
    };
};
