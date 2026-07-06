package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.repositories.IEmailRepository;
import ar.utn.ba.ddsi.mailing.services.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final IEmailRepository emailRepository;


    private final JavaMailSender mailSender;

    public EmailService(IEmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Override
    public Email crearEmail(Email email) {
        return emailRepository.save(email);
    }

    @Override
    public List<Email> obtenerEmails(Boolean pendiente) {
        if (pendiente != null) {
            return emailRepository.findByEnviado(!pendiente);
        }
        return emailRepository.findAll();
    }

    @Override
    public void procesarPendientes() {
        List<Email> pendientes = emailRepository.findByEnviado(false);
        for (Email email : pendientes) {
            try {
                SimpleMailMessage mensajeReal = new SimpleMailMessage();
                mensajeReal.setFrom(email.getRemitente());
                mensajeReal.setTo(email.getDestinatario());
                mensajeReal.setSubject(email.getAsunto());


                mensajeReal.setText(email.getContenido());


                mailSender.send(mensajeReal);


                email.setEnviado(true);
                emailRepository.save(email);
                logger.info("Correo enviado exitosamente de verdad a: {}", email.getDestinatario());

            } catch (Exception e) {
                logger.error("Fallo al enviar correo a {}: {}", email.getDestinatario(), e.getMessage());
            }
        }
    }

    @Override
    public void loguearEmailsPendientes() {
        List<Email> pendientes = obtenerEmails(true);
        logger.info("Emails pendientes de envío: {}", pendientes.size());
        pendientes.forEach(email ->
            logger.info("Email pendiente - ID: {}, Destinatario: {}, Asunto: {}",
                email.getId(),
                email.getDestinatario(),
                email.getAsunto())
        );
    }
}