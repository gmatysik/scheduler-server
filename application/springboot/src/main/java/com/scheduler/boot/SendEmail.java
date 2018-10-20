/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
public class SendEmail {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendMail(String to,String body) {

        System.out.println("Sending email...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("grzegorz.matysik@gmail.com");
        message.setSubject("Confirm appointment");
        message.setText(body);
        mailSender.send(message);
        System.out.println("Email Sent!");
    }

}
