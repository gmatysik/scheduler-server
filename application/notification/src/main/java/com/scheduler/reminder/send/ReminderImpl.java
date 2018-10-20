/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.reminder.send;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.Task;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public class ReminderImpl implements Reminder{

    private ReminderSender sender;

    private Task tasks;
    
    private ReminderValidator validator;

    public ReminderImpl(ReminderSender sender, Task tasks, ReminderValidator validator){
        this.sender = sender;
        this.tasks = tasks;
        this.validator = validator;
    }
    
    @Override
    public void sendTaskNotificationToUser(TaskDTO task, long userId) {
        //tasks.getTasksFromNextSevenDaysForUser(user);
        //sender.sendTaskNotificationToUser(task, user);
    }

    @Override
    public void sendNextSevenDaysReminderForUser(long userId) throws ReminderValidationException{
        List<TaskDTO> taskList = tasks.getTasksFromNextSevenDaysForUser(userId);
        validator.validate(taskList);
        sender.sendTaskNotificationToUser(taskList, userId);
    }

}
