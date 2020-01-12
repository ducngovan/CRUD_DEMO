package com.sinfloo.ejemplo01;

import com.sinfloo.ejemplo01.Service.Impl.PersonaServiceImpl;
import com.sinfloo.ejemplo01.Service.PersonaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    PersonaService personaService(){
        return new PersonaServiceImpl();
    }
}
