/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.reminder.send;

import com.scheduler.tasks.TaskDTO;

/**
 *
 * @author Grzegorz
 */
public interface Reminder {
        public void sendTaskNotificationToUser(TaskDTO task, long userId);
        
        public void sendNextSevenDaysReminderForUser(long userId) throws ReminderValidationException;
}
