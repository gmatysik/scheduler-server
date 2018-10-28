/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

/**
 *
 * @author Grzegorz
 */
public interface EmailSender {
    
    public void sendMail(String to, String subject, String body);
}
