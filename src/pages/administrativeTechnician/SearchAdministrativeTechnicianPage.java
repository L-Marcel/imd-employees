package src.pages.administrativeTechnician;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.models.AdministrativeTechnician;
import src.utils.Registrations;

public class SearchAdministrativeTechnicianPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to search administrative technician page.");
        menu.header("Buscando Técnico Adm.");

        Long registration = menu.getLong(
            "Matrícula: ", 
            (t) -> Registrations.validate(t)
        );

        menu.divider();

        AdministrativeTechnician administrativeTechnician = Employees.getAdministrativeTechnicianByRegistration(
            registration
        );

        if (administrativeTechnician == null) {
            menu.push("Nenhum técnico encontrado!");
            menu.divider();
            menu.pushPageBack();
            router.back();
        } else {
            router.replace(new AdministrativeTechnicianPage(administrativeTechnician));
        };
    };
};
