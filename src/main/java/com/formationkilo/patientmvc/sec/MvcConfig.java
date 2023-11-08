package com.formationkilo.patientmvc.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig  implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("patients");
        registry.addViewController("/").setViewName("patients");
        registry.addViewController("/formPatients").setViewName("formPatients");

    }
}
