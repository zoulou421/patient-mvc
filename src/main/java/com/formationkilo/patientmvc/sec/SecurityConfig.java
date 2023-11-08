package com.formationkilo.patientmvc.sec;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig  {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
       return
              new InMemoryUserDetailsManager(
                    /*
                      User.withUsername("user1").password("{noop}1234").roles("USER").build(),
                      User.withUsername("user2").password("{noop}1234").roles("USER").build(),
                      User.withUsername("admin").password("{noop}1234").roles("USER","ADMIN").build()
                     */
                      User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                      User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                      User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()


              );

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //If this endpoint is a Spring MVC endpoint, please use requestMatchers(MvcRequestMatcher);
        //
         httpSecurity

                 .authorizeHttpRequests((requests) -> requests
                         .requestMatchers(AntPathRequestMatcher.antMatcher("/user/**")).hasRole("USER")
                         .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN")
                         .anyRequest().authenticated()
                 )
                 .formLogin(withDefaults());//default form spring used

        httpSecurity
                // sample exception handling customization
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/access-denied")
                );

        return httpSecurity.build();
    }

}
