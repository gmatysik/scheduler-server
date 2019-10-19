/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.users;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.validation.TaskValidationException;

import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface User {

    public List<UserDTO> getAll();

    public UserDTO get(long id);

    public UserDTO add(UserDTO task) throws TaskValidationException;

    public UserDTO update(UserDTO task) throws TaskValidationException;

    public void remove(long taskId);

}
