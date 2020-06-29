package hr.tvz.gredisnjak.studapp;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

public class QuartzJob extends QuartzJobBean {

    private final StudentService studentService;

    List<StudentDTO> listaUpisanihStudenata = new ArrayList<>();

    public QuartzJob(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        listaUpisanihStudenata = studentService.findAll();
        System.out.println("Ovo su trenutno upisani studenti");
        System.out.println("-------------------------------------");

        for(int i = 0; i < listaUpisanihStudenata.size(); i++){
            System.out.println(listaUpisanihStudenata.get(i).getJmbag() + " - "
                    + listaUpisanihStudenata.get(i).getFirstName() + " " + listaUpisanihStudenata.get(i).getLastName());
        }
        System.out.println("-------------------------------------");
    }
}
