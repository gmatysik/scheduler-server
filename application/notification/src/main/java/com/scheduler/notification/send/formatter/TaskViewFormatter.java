/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send.formatter;

import com.scheduler.tasks.TaskDTO;

/**
 *
 * @author Grzegorz
 */
public interface TaskViewFormatter {
    
    public String format(TaskDTO task);
}
