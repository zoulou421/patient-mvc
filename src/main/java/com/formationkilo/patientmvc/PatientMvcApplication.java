package com.formationkilo.patientmvc;

import com.formationkilo.patientmvc.dao.PatientRepository;
import com.formationkilo.patientmvc.dto.Patient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

   // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args-> {
           patientRepository.save(new
                   Patient(null,"Kendra1",new Date(),false,12));
            patientRepository.save(new
                    Patient(null,"Kendra2",new Date(),true,13));
            patientRepository.save(new
                    Patient(null,"Kendra3",new Date(),false,14));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
