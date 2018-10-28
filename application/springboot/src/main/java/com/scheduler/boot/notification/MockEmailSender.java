/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Grzegorz
 */
@Service
@Profile("development")
public class MockEmailSender implements EmailSender{
    
    private final static Logger LOGGER = LogManager.getLogger(MockEmailSender.class);
    
    @Override
    public void sendMail(String to, String subject, String body) {
        LOGGER.info("Sending email: ");
        LOGGER.info("===================================================");
        LOGGER.info("To: " + to);
        LOGGER.info("Subject: " + subject);
        LOGGER.info("Body: " + body);
        LOGGER.info("===================================================");
    }
    
}
