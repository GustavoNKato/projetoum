package com.kato.projetoum.services;

import org.springframework.mail.SimpleMailMessage;

import com.kato.projetoum.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
