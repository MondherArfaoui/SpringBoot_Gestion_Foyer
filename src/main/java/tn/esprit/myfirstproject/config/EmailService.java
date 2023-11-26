package tn.esprit.myfirstproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Mail;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gestionfoyer.com");
        message.setTo(mail.getTo());
        message.setSubject("Réinitialiser le mot de passe");
        message.setText(mail.getContent());
        mailSender.send(message);
    }
}
