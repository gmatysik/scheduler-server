/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks.validation;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskValidationException;
import com.scheduler.tasks.TaskValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Grzegorz
 */
public class TaskValidatorImpl implements TaskValidator{
    @Override
    public void validate(TaskDTO task) throws TaskValidationException{
        List<String> messages = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(task);
        violations.stream().forEach(constraint -> messages.add(constraint.getMessage()));
        
        if(!messages.isEmpty()){
            throw new TaskValidationException(messages);
        }
    }
}
