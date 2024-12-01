package src.pages.teacher;

import java.time.LocalDate;
import java.util.ArrayList;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import pretty.layout.Text;
import pretty.utils.Array;
import src.log.Log;
import src.persons.Disciplines;
import src.persons.Employees;
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

        menu.header("Disciplinas");
        ArrayList<String> teacherDisciplines = new ArrayList<String>();

        String[] disciplinesOptions = {
            "Adiciona disciplina",
            "Remover disciplina"
        };

        boolean addingDisciplines = true;
        while(addingDisciplines) {
            for (String discipline : teacherDisciplines) {
                menu.push(Text.success("+ ") + discipline);
            };
            
            int option = 0;
            if (teacherDisciplines.size() > 0) {
                menu.divider();
                option = menu.getPageOption(disciplinesOptions, "Finalizar");
                menu.rollback(7);
            };

            String[] teacherDisciplinesArray = teacherDisciplines.toArray(String[]::new);
            Disciplines disciplines = Disciplines.getInstance();

            switch (option) {
                case -1: {
                    addingDisciplines = false;
                    disciplines.decrement(teacherDisciplinesArray);
                    break;
                } case 0: {
                    String[] mappedDisicplines = disciplines.getArray();

                    ArrayList<String> availableDisciplines = new ArrayList<String>(
                        mappedDisicplines.length
                    );

                    for (String mappedDiscipline : mappedDisicplines) {
                        availableDisciplines.add(mappedDiscipline);
                    };

                    availableDisciplines.add("[Cadastrar nova disciplina]");
                    if (teacherDisciplines.size() > 0) menu.divider();

                    String[] avaiableDisciplinesArray = availableDisciplines
                        .stream()
                        .filter((t) -> !Array.exists(
                            teacherDisciplinesArray, 
                            t
                        ))
                        .toArray(String[]::new);

                    int selected = menu.getOption(
                        "Associar ao professor: ", 
                        avaiableDisciplinesArray
                    );
                    if (teacherDisciplines.size() > 0) menu.rollback(2);
                    else menu.rollback();

                    String discipline;
                    if (avaiableDisciplinesArray.length - 1 <= selected) {
                        discipline = menu.getString(
                            "Nova disciplina: ", 
                            (t) -> disciplines.validate(t)
                        );
                        menu.rollback();
                    } else {
                        discipline = avaiableDisciplinesArray[selected];
                    };

                    teacherDisciplines.add(discipline);
                    disciplines.increment(discipline);
                    menu.rollback(teacherDisciplines.size() - 1);
                    break;
                } case 1: {
                    menu.rollback(teacherDisciplines.size());
                    int selected = menu.getOption(
                        "Desassociar do professor: ", 
                        teacherDisciplinesArray
                    );
                    menu.rollback();
                    disciplines.decrement(teacherDisciplines.get(selected));
                    teacherDisciplines.remove(selected);
                    break;
                } default: {
                    menu.rollback(teacherDisciplines.size());
                    break;
                }
            };
        };
        
        menu.divider();
        boolean confirmation = menu.getPageConfirmation();
        if (confirmation) {
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
                teacherDisciplines
            );

            Employees.addTeacher(teacher);
        };

        router.back();
    };
};
