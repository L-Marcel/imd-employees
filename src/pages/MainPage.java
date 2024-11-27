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
            "Informações"
        };
        
        int option = -1;
        menu.header("Menu Principal");
        option = menu.getPageOption(options, true);
        menu.divider();

        switch(option) {
            case -1:
                router.back();
                break;
            case 0:
                router.navigate(new InfomationPage());
                break;
            default:
                router.replace(this);
                break;
        };
    };
};
