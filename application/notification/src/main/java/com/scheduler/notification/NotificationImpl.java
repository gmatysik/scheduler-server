/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.notification.send.validation.NotificationValidationException;
import com.scheduler.notification.send.validation.NotificationValidator;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.Task;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
class NotificationImpl implements Notification{

    private final NotificationSender sender;

    private final Task tasks;
    
    private final NotificationValidator validator;

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
