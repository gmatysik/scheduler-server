/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification;

import com.scheduler.notification.send.validation.NotificationValidationException;
import com.scheduler.users.UserDTO;

/**
 *
 * @author Grzegorz
 */
public interface Notification {
        
        public void sendNextSevenDaysReminderForUser(UserDTO userId) throws NotificationValidationException;
}
