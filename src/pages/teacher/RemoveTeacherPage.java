package src.pages.teacher;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.models.Teacher;
import src.utils.Registrations;

public class RemoveTeacherPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to remove teacher page.");
        menu.header("Removendo Professor");

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
            menu.push("Deseja mesmo remover " + teacher.getName() + "?");
            boolean confirmation = menu.getPageConfirmation("Cancelar");
            if (confirmation) {
                Employees.removeTeacher(teacher);
                menu.push("Professor " + teacher.getName() + " removido!");
            };
            router.back();
        };
    };
};
