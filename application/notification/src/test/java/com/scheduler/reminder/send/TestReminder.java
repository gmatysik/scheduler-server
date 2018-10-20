/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.reminder.send;

import com.scheduler.reminder.send.validation.ReminderValidatorImpl;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.Task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Grzegorz
 */
public class TestReminder {
    
    private Reminder reminder;
    
    private ReminderSender sender;
    
    private Task tasks;
    
    private List<TaskDTO> taskList = new ArrayList<>();
    
    private long user;
    
    @Before
    public void setUp(){
        user = 0;
        
        TaskDTO task = new TaskDTO();
        task.setTitle("Task 1");
        DateFormat df = new SimpleDateFormat(TaskDTO.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.MINUTE, 1);
        task.setStart(df.format(calendar.getTime()));
        taskList.add(task);
        
        sender = Mockito.mock(ReminderSender.class);
        tasks = Mockito.mock(Task.class);
        Mockito.when(tasks.getTasksFromNextSevenDaysForUser(user)).thenReturn(taskList);

        reminder = new ReminderImpl(sender, tasks, new ReminderValidatorImpl());
    }
    
    @Test
    public void testSendNextSevenDaysReminderForUser() throws ReminderValidationException{
        reminder.sendNextSevenDaysReminderForUser(user);
        Mockito.verify(sender, Mockito.times(1)).sendTaskNotificationToUser(taskList, user);
    }
    
    @Test
    public void testSendNextSevenDaysReminderForUser_exception() throws ReminderValidationException{
        DateFormat df = new SimpleDateFormat(TaskDTO.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 8);
        calendar.add(Calendar.MINUTE, 1);
        
        taskList.get(0).setStart(df.format(calendar.getTime()));
        try{
            reminder.sendNextSevenDaysReminderForUser(user);   
            Assert.fail();
        } catch(ReminderValidationException ex){
            Mockito.verify(sender, Mockito.times(0)).sendTaskNotificationToUser(taskList, user);            
        }
        Mockito.verify(sender, Mockito.times(0)).sendTaskNotificationToUser(taskList, user);
    }    
}
