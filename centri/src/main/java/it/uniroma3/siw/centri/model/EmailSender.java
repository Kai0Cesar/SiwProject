package it.uniroma3.siw.centri.model;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String oggetto,String messaggio) throws MessagingException {
		MimeMessage message =javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(oggetto);
		helper.setTo(to);
		helper.setText(messaggio, true);
		javaMailSender.send(message);
	}

}
