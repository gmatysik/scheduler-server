/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks.validation;

import com.scheduler.tasks.TaskDTO;

/**
 *
 * @author Grzegorz
 */
public interface TaskValidator {
    public void validate(TaskDTO task) throws TaskValidationException;
}