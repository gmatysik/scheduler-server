package com.scheduler.users;


import com.scheduler.tasks.validation.TaskValidationException;
import com.scheduler.users.repository.UserRepository;

import java.util.List;

public class UserImpl implements User {

    private UserRepository userRepository;

    public UserImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDTO get(long id) {
        return userRepository.get(id);
    }

    @Override
    public UserDTO add(UserDTO task) throws TaskValidationException {
        return userRepository.create(task);
    }

    @Override
    public UserDTO update(UserDTO task) throws TaskValidationException {
        return userRepository.update(task);
    }

    @Override
    public void remove(long taskId) {
        userRepository.remove(taskId);
    }
}
