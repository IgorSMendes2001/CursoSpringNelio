package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
