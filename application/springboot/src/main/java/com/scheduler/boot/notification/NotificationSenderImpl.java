/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.notification.send.formatter.TaskListViewFormatter;
import com.scheduler.notification.send.formatter.TaskViewFormatter;
import com.scheduler.tasks.TaskDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
public class NotificationSenderImpl extends NotificationSender{
    
    @Autowired
    private EmailSender sendEmail;

    public NotificationSenderImpl() {
        super(new NotificationListFormatterImpl(new TaskNotificationFormatterImpl()));
    }
    
    @Override
    public void sendTaskNotification(String fomattedNotification) {
        sendEmail.sendMail("jomjom@poczta.onet.pl", "Tasks list", fomattedNotification);    
    }
    
        
}
