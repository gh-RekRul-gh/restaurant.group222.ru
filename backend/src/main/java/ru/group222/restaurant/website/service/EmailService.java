package ru.group222.restaurant.website.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailService {
    private final JavaMailSender javaMailSender;

    public static final String PASSWORD_RESET_MESSAGE = """
            Hello there,
            Your password reset code is:
                           \s
            %s
                           \s
            This code will expire in 15 minutes""";

    public static final String SUPPORT_CONTACT_MESSAGE = """
            Hello there, %s,
            We have received your support message and will try to respond within a couple of business days""";

    public static final String ORDER_CREATED_MESSAGE = """
            Hello there, %s,
            Your order № %d was successfully created""";

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
        log.info("Sent an email to {}", recipient);
    }
}
