/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.notification.send.validation.NotificationValidationException;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.Task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.scheduler.users.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Grzegorz
 */
public class TestNotification {
    
    private Notification reminder;
    
    private NotificationSender sender;
    
    private Task tasks;
    
    private List<TaskDTO> taskList = new ArrayList<>();
    
    private UserDTO user;
    
    @Before
    public void setUp(){
        user = new UserDTO();
        user.setId(0);
        
        TaskDTO task = new TaskDTO();
        task.setTitle("Task 1");
        DateFormat df = new SimpleDateFormat(TaskDTO.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.MINUTE, 1);
        task.setStart(df.format(calendar.getTime()));
        taskList.add(task);
        
        sender = Mockito.mock(NotificationSender.class);
        Mockito.doNothing().when(sender).sendTaskNotificationToUser(Mockito.anyList(), Mockito.eq(0L));
        tasks = Mockito.mock(Task.class);
        Mockito.when(tasks.getTasksFromNextSevenDaysForUser(user.getId())).thenReturn(taskList);

        reminder = NotificationFactory.createNotification(sender, tasks);                
    }
    
    @Test
    public void testSendNextSevenDaysReminderForUser() throws NotificationValidationException{
        reminder.sendNextSevenDaysReminderForUser(user);
        Mockito.verify(sender, Mockito.times(1)).sendTaskNotificationToUser(taskList, user.getId());
    }
    
    @Test
    public void testSendNextSevenDaysReminderForUser_exception() throws NotificationValidationException{
        DateFormat df = new SimpleDateFormat(TaskDTO.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Task.DAYS_TO_FIND_TASKS + 1);
        calendar.add(Calendar.MINUTE, 1);
        
        taskList.get(0).setStart(df.format(calendar.getTime()));
        try{
            reminder.sendNextSevenDaysReminderForUser(user);   
            Assert.fail();
        } catch(NotificationValidationException ex){
            Assert.assertTrue(ex.getMessage().contains("Task Task 1 start date too early/too late"));
        }
      Mockito.verify(sender, Mockito.times(0)).sendTaskNotificationToUser(taskList, user.getId());
    }
}
