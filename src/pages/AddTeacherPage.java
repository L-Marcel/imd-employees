package src.pages;

import java.time.LocalDate;
import java.util.ArrayList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Persons;
import src.persons.common.Address;
import src.persons.enums.Genrer;
import src.persons.enums.Graduation;
import src.persons.enums.Level;
import src.persons.models.Teacher;
import src.utils.Genrers;
import src.utils.Graduations;
import src.utils.Levels;
import src.utils.Names;
import src.utils.Registrations;
import src.utils.Wages;
import src.utils.Workloads;
import src.utils.Addresses;
import src.utils.Ceps;
import src.utils.Cpfs;
import src.utils.Dates;
import src.utils.Departaments;

public class AddTeacherPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to add teacher page.");
        
        menu.header("Cadastrando Professor");
        String name = menu.getString(
            "Nome: ", 
            (t) -> Names.validate(t)
        );
        String cpf = Cpfs.format(
            menu.getString(
                "CPF: ", 
                (t) -> Cpfs.validate(t), 
                (t) -> Cpfs.format(t)
            )
        );
        Long registration = menu.getLong(
            "Matricula: ", 
            (t) -> Registrations.validate(t)
        );
        LocalDate birthdate = Dates.fromString(
            menu.getString(
                "Data de nascimento: ", 
                (t) -> Dates.validate(t)
            )
        );
        Genrer genrer = Genrers.convert(
            menu.getOption(
                "Gênero: ", 
                Genrers.getOptions()
            )
        );

        menu.header("Endereço");
        String street = menu.getString(
            "Rua: ", 
            (t) -> Addresses.validateStreet(t)
        );
        int number = menu.getInt(
            "Número: ", 
            (t) -> Addresses.validateNumber(t)
        );
        String city = menu.getString(
            "Cidade: ", 
            (t) -> Addresses.validateCity(t)
        );
        String district = menu.getString(
            "Bairro: ", 
            (t) -> Addresses.validateDistrict(t)
        );
        String cep = Ceps.format(
            menu.getString(
                "CEP: ", 
                (t) -> Ceps.validate(t),
                (t) -> Ceps.format(t)
            )
        );
        Address address = new Address(
            street,
            number,
            city,
            district,
            cep
        );

        menu.header("Trabalho");
        Double wage = menu.getDouble(
            "Salário (sem os bonus): ", 
            (t) -> Wages.validate(t, 4000d),
            (t) -> Wages.format(t)
        );
        String departament = menu.getString(
            "Departamento: ", 
            (t) -> Departaments.validate(t)
        );
        Integer workload = menu.getInt(
            "Carga horária: ", 
            (t) -> Workloads.validate(t),
            (t) -> Workloads.format(t)
        );
        LocalDate joinedAt = Dates.fromString(
            menu.getString(
                "Data de ingresso: ", 
                (t) -> Dates.validate(t),
                (t) -> Dates.format(Dates.fromString(t))
            )
        );

        menu.header("Formação");
        Level level = Levels.convert(
            menu.getOption(
                "Nível: ", 
                Levels.getOptions()
            )
        );
        Graduation graduation = Graduations.convert(
            menu.getOption(
                "Graduação: ", 
                Graduations.getOptions()
            )
        );
        menu.divider();
        
        Teacher teacher = new Teacher(
            name,
            cpf,
            birthdate,
            genrer,
            address,
            registration,
            wage,
            departament,
            workload,
            joinedAt,
            level,
            graduation,
            new ArrayList<>()
        );

        boolean confirmation = menu.getPageConfirmation();
        if (confirmation) {
            Persons persons = Persons.getInstance();
            persons.add(teacher);
        } else {
            router.back();
        };
    };
};
