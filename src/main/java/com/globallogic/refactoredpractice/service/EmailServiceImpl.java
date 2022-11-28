package com.globallogic.refactoredpractice.service;

import com.globallogic.refactoredpractice.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void seMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    // Send simple email method
    public ResponseEntity sendMail(Email email) {
        // Create new mailMessage object

        SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);

        // Setting message variables
        message.setTo(email.getRecipient());
        message.setText(email.getBody());
        message.setSubject(email.getSubject());
        try {
            // Send the email
            this.mailSender.send(message);
            return ResponseEntity.ok("Mail sent successfully");
        } catch (MailException e) {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
