package src.pages;

import java.util.LinkedList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.models.AdministrativeTechnician;

public class AdministrativeTechniciansPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to administrative technicians page.");
        menu.header("Técnico Administrativos");

        LinkedList<AdministrativeTechnician> administrativeTechnicians = 
            Employees.getAdministrativeTechnicians();
            
        String[] options = administrativeTechnicians.stream()
            .map((t) -> t.toString())
            .toArray(String[]::new);

        if (options.length <= 0) {
            menu.push("Não há técnicos cadastrados!");
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
