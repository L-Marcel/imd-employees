package src.pages;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.Persons;
import src.persons.models.AdministrativeTechnician;
import src.utils.Registrations;

public class RemoveAdministrativeTechnicianPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to remove administrative technician page.");
        menu.header("Removendo Técnico Adm.");

        Long registration = menu.getLong(
            "Matrícula: ", 
            (t) -> Registrations.validate(t)
        );

        menu.divider();

        AdministrativeTechnician teacher = Employees.getAdministrativeTechnicianByRegistration(
            registration
        );

        if (teacher == null) {
            menu.push("Nenhum técnico encontrado!");
            menu.divider();
            menu.pushPageBack();
            router.back();
        } else {
            menu.push("Deseja mesmo remover " + teacher.getName() + "?");
            boolean confirmation = menu.getPageConfirmation("Cancelar");
            if (confirmation) {
                Persons persons = Persons.getInstance();
                persons.remove(teacher);
                menu.push("Técnico " + teacher.getName() + " removido!");
            };
            router.back();
        };
    };
};
