package src.pages;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;

public class MainPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to main page.");
        
        String[] options = {
            "Cadastrar professor",
            "Cadastrar técnico administrativo",
            "Listar professores",
            "Lista técnicos administrativos",
            "Buscar professor",
            "Buscar técnico administrativo",
            "Remover professor",
            "Remover técnico administrativo",
            "Informações",
        };
        
        int option = -1;
        menu.header("Menu Principal");
        option = menu.getPageOption(options);
        menu.divider();

        switch(option) {
            case -1:
                router.back();
                break;
            case 0:
                router.navigate(new AddTeacherPage());
                break;
            case 1:
                router.navigate(new AddAdministrativeTechnicianPage());
                break;
            case 2:
                router.navigate(new TeachersPage());
                break;
            case 3:
                router.navigate(new AdministrativeTechniciansPage());
                break;
            case 4:
                router.navigate(new SearchTeacherPage());
                break;
            case 5:
                router.navigate(new SearchAdministrativeTechnicianPage());
                break;
            case 6:
                router.navigate(new RemoveTeacherPage());
                break;
            case 7:
                router.navigate(new RemoveAdministrativeTechnicianPage());
                break;
            case 8:
                router.navigate(new InfomationPage());
                break;
            default:
                router.replace(this);
                break;
        };
    };
};
