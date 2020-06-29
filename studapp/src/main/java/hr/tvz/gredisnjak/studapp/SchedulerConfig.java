package hr.tvz.gredisnjak.studapp;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail upisaniJobDetail(){
        return JobBuilder.newJob(QuartzJob.class).withIdentity("upisani studenti").storeDurably().build();
    }

    @Bean
    public Trigger objavaJobTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(upisaniJobDetail()).withIdentity("studentiTrigger").withSchedule(scheduleBuilder).build();
    }
}
