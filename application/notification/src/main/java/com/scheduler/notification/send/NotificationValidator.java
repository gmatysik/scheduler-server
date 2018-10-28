/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send;

import com.scheduler.tasks.TaskDTO;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface NotificationValidator {
        public void validate(List<TaskDTO> task) throws NotificationValidationException;

}
