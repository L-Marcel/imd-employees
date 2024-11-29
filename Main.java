import pretty.Router;
import src.ShutdownHook;
import src.pages.MainPage;
import src.persons.Persons;
import src.persons.models.Teacher;
import src.utils.Ceps;
import src.utils.Cpfs;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        Persons persons = Persons.getInstance();
        Router router = Router.getInstance();
        router.start(new MainPage());

        // Needed by threads
        System.exit(0);
    };
};