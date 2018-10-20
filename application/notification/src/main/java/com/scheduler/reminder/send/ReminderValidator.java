/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.reminder.send;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskValidationException;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface ReminderValidator {
        public void validate(List<TaskDTO> task) throws ReminderValidationException;

}
