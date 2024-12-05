package src.pages.teacher;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import pretty.layout.Text;
import src.log.Log;
import src.persons.models.Teacher;
import src.utils.Ceps;
import src.utils.Dates;
import src.utils.Genrers;
import src.utils.Graduations;
import src.utils.Levels;
import src.utils.Wages;
import src.utils.Workloads;

public class TeacherPage implements Page {
    private Teacher teacher;

    public TeacherPage(Teacher teacher) {
        this.teacher = teacher;
    };

    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigation to teacher page.");

        menu.header("Professor");
        menu.push("Nome: " + Text.highlight(this.teacher.getName()));
        menu.push("CPF: " + Text.highlight(this.teacher.getCpf()));
        menu.push("Matrícula: " + Text.highlight(this.teacher.getRegistration()));
        menu.push("Data de nascimento: " + Text.highlight(Dates.format(this.teacher.getBirthdate())));
        menu.push("Gênero: " + Text.highlight(Genrers.toString(this.teacher.getGenrer())));
        
        menu.header("Endereço");
        menu.push("Rua: " + Text.highlight(this.teacher.getAddress().getStreet()));
        menu.push("Número: " + Text.highlight(this.teacher.getAddress().getNumber()));
        menu.push("Cidade: " + Text.highlight(this.teacher.getAddress().getCity()));
        menu.push("Bairro: " + Text.highlight(this.teacher.getAddress().getDistrict()));
        menu.push("CEP: " + Text.highlight(Ceps.format(this.teacher.getAddress().getCep())));

        menu.header("Formação");
        menu.push("Nível: " + Text.highlight(Levels.toString(this.teacher.getLevel())));
        menu.push("Graduação: " + Text.highlight(Graduations.toString(this.teacher.getGraduation())));
        
        menu.header("Trabalho");
        menu.push("Departamento: " + Text.highlight(this.teacher.getDepartment()));
        menu.push("Carga horária: " + Text.highlight(Workloads.format(this.teacher.getWorkload())));
        menu.push("Data de ingresso: " + Text.highlight(Dates.format(this.teacher.getJoinedAt())));
        
        menu.header("Disciplinas");
        for (String discipline : teacher.getDisciplines()) {
            menu.push(discipline);
        };

        menu.header("Salário");
        menu.push("Base: " + Text.highlight(Wages.format(this.teacher.getWage() + this.teacher.getGraduationBonus())));
        menu.push(" Graduação: " + Text.highlight("+ " + Wages.format(this.teacher.getGraduationBonus())));
        menu.push("Total: " + Text.highlight(Wages.format(this.teacher.getWageWithBonus())));
        menu.push("  Nível: " + Text.highlight("+ " + Wages.format(this.teacher.getLevelBonus())));
        

        menu.divider();
        menu.pushPageBack();
        
        router.back();
    };
};
