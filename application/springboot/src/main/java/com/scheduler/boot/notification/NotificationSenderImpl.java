/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.users.User;
import com.scheduler.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Grzegorz
 */
@Component
public class NotificationSenderImpl extends NotificationSender {

    private EmailSender sendEmail;

    @Autowired
    public NotificationSenderImpl(User user, EmailSender sendEmail) {
        super(new NotificationListFormatterImpl(new TaskNotificationFormatterImpl()), user);
        this.sendEmail = sendEmail;
    }

    @Override
    public void sendTaskNotification(String fomattedNotification, UserDTO userDTO) {
        sendEmail.sendMail("jomjom@poczta.onet.pl", "Tasks list", fomattedNotification);
    }


}
