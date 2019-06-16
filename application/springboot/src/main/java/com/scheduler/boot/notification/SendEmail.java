/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
@Profile({"production","development_email"})
public class SendEmail implements EmailSender{
    
    private final static Logger LOGGER = LogManager.getLogger(SendEmail.class);
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void sendMail(String to, String subject, String body) {

        LOGGER.info("Sending email...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("grzegorz.matysik@gmail.com");
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        LOGGER.info("Email Sent!");
    }

}
