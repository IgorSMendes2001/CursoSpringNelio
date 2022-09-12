package com.devsuperior.cursospring.config;

import com.devsuperior.cursospring.services.DBService;
import com.devsuperior.cursospring.services.EmailService;
import com.devsuperior.cursospring.services.SMTPEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public EmailService emailService(){
        return new SMTPEmailService();
    }
    @Bean
    public boolean instanciateDatabase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }
        dbService.instanciateTestDatabase();
        return true;
    }
}
