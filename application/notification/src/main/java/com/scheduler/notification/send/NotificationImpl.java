/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grzegorz
 */
public class NotificationImpl implements Notification{

    private NotificationSender sender;

    private Task tasks;
    
    private NotificationValidator validator;

    public NotificationImpl(NotificationSender sender, Task tasks, NotificationValidator validator){
        this.sender = sender;
        this.tasks = tasks;
        this.validator = validator;
    }
    
    @Override
    public void sendNextSevenDaysReminderForUser(long userId) throws NotificationValidationException{
        List<TaskDTO> taskList = tasks.getTasksFromNextSevenDaysForUser(userId);
        validator.validate(taskList);
        sender.sendTaskNotificationToUser(taskList, userId);
    }


}
