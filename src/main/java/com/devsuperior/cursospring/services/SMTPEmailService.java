package com.devsuperior.cursospring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService{
    private static final Logger log = LoggerFactory.getLogger(SMTPEmailService.class);
    @Autowired
    private MailSender mailSender;
    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de email ...");
        mailSender.send(msg);
        log.info("Email enviado!");
    }
}
