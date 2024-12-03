package src.pages.administrativeTechnician;

import java.time.LocalDate;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import src.log.Log;
import src.persons.Employees;
import src.persons.common.Address;
import src.persons.enums.Genrer;
import src.persons.enums.Graduation;
import src.persons.enums.Level;
import src.persons.models.AdministrativeTechnician;
import src.utils.Genrers;
import src.utils.Graduations;
import src.utils.Levels;
import src.utils.Names;
import src.utils.Registrations;
import src.utils.Workloads;
import src.utils.Addresses;
import src.utils.Ceps;
import src.utils.Cpfs;
import src.utils.Dates;
import src.utils.Departaments;

public class AddAdministrativeTechnicianPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigating to add administrative technician page.");

        menu.header("Cadastrando Técnico Adm.");
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
            "Matrícula: ", 
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
        // Double wage = menu.getDouble(
        //     "Salário (sem os bonus): ", 
        //     (t) -> Wages.validate(t, 2500d),
        //     (t) -> Wages.format(t)
        // );
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

        menu.header("Adicionais");
        String[] yesOrNoOptions = {"Sim", "Não"};
        Boolean unhealthiness = 0 == menu.getOption("Insalubridade: ", yesOrNoOptions);
        Boolean gratification = 0 == menu.getOption("Gratificação: ", yesOrNoOptions);
        
        menu.divider();
        boolean confirmation = menu.getPageConfirmation();
        if (confirmation) {
            AdministrativeTechnician administrativeTechnician = 
            new AdministrativeTechnician(
                name,
                cpf,
                birthdate,
                genrer,
                address,
                registration,
                2500d,
                departament,
                workload,
                joinedAt,
                level,
                graduation,
                unhealthiness,
                gratification
            );

            Employees.addAdministrativeTechnician(administrativeTechnician);
        };

        router.back();
    };
};
