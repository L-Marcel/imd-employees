package src.pages.administrativeTechnician;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import pretty.layout.Text;
import src.log.Log;
import src.persons.models.AdministrativeTechnician;
import src.utils.Ceps;
import src.utils.Dates;
import src.utils.Genrers;
import src.utils.Graduations;
import src.utils.Levels;
import src.utils.Wages;
import src.utils.Workloads;

public class AdministrativeTechnicianPage implements Page {
    private AdministrativeTechnician administrativeTechnician;

    public AdministrativeTechnicianPage(
        AdministrativeTechnician administrativeTechnician
    ) {
        this.administrativeTechnician = administrativeTechnician;
    };

    @Override
    public void render(Menu menu, Router router) {
        Log.print("Navigation to administrative technician page.");

        menu.header("Professor");
        menu.push("Nome: " + Text.highlight(this.administrativeTechnician.getName()));
        menu.push("CPF: " + Text.highlight(this.administrativeTechnician.getCpf()));
        menu.push("Matrícula: " + Text.highlight(this.administrativeTechnician.getRegistration()));
        menu.push("Data de nascimento: " + Text.highlight(Dates.format(this.administrativeTechnician.getBirthdate())));
        menu.push("Gênero: " + Text.highlight(Genrers.toString(this.administrativeTechnician.getGenrer())));
        
        menu.header("Endereço");
        menu.push("Rua: " + Text.highlight(this.administrativeTechnician.getAddress().getStreet()));
        menu.push("Número: " + Text.highlight(this.administrativeTechnician.getAddress().getNumber()));
        menu.push("Cidade: " + Text.highlight(this.administrativeTechnician.getAddress().getCity()));
        menu.push("Bairro: " + Text.highlight(this.administrativeTechnician.getAddress().getDistrict()));
        menu.push("CEP: " + Text.highlight(Ceps.format(this.administrativeTechnician.getAddress().getCep())));

        menu.header("Formação");
        menu.push("Nível: " + Text.highlight(Levels.toString(this.administrativeTechnician.getLevel())));
        menu.push("Graduação: " + Text.highlight(Graduations.toString(this.administrativeTechnician.getGraduation())));
        
        menu.header("Adicionais");
        menu.push("Insalubridade: " + Text.highlight((this.administrativeTechnician.getUnhealthiness()? "Sim":"Não")));
        menu.push("Gratificação: " + Text.highlight((this.administrativeTechnician.getGratification()? "Sim":"Não")));

        menu.header("Trabalho");
        menu.push("Departamento: " + Text.highlight(this.administrativeTechnician.getDepartment()));
        menu.push("Carga horária: " + Text.highlight(Workloads.format(this.administrativeTechnician.getWorkload())));
        menu.push("Data de ingresso: " + Text.highlight(Dates.format(this.administrativeTechnician.getJoinedAt())));
        menu.push("Salário (sem os bonus): " + Text.highlight(Wages.format(this.administrativeTechnician.getWage())));
        menu.push("Salário (com os bonus): " + Text.highlight(Wages.format(this.administrativeTechnician.getWageWithBonus())));
        menu.push("  Nível: " + Text.highlight("+ " + Wages.format(this.administrativeTechnician.getLevelBonus())));
        menu.push("  Graduação: " + Text.highlight("+ " + Wages.format(this.administrativeTechnician.getGraduationBonus())));
        menu.push("  Insalubridade: " + Text.highlight("+ " + Wages.format(this.administrativeTechnician.getUnhealthinessBonus())));
        menu.push("  Gratification: " + Text.highlight("+ " + Wages.format(this.administrativeTechnician.getGratificationBonus())));
        
        menu.divider();
        menu.pushPageBack();
        
        router.back();
    };
};
