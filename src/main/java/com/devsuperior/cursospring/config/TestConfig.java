package com.devsuperior.cursospring.config;

import com.devsuperior.cursospring.services.DBService;
import com.devsuperior.cursospring.services.EmailService;
import com.devsuperior.cursospring.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        dbService.instanciateTestDatabase();
        return true;
    }
    @Bean
    public EmailService mockEmailService(){
        return new MockEmailService();
    }
}
