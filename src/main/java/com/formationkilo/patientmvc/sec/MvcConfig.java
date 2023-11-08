package com.formationkilo.patientmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig  implements WebMvcConfigurer {
  /*  public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/index").setViewName("patients");
        registry.addViewController("/user").setViewName("patients");
        registry.addViewController("/admin/formPatients").setViewName("formPatients");

    }*/
}
