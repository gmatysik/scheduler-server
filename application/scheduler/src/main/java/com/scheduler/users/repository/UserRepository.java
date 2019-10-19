package com.scheduler.users.repository;

import com.scheduler.users.UserDTO;

import java.util.List;

public interface UserRepository {

    public List<UserDTO> getAll();

    public UserDTO get(long id);

    public UserDTO create(UserDTO object);

    public UserDTO update(UserDTO object);

    public void remove(long id);

    public UserDTO find(UserDTO search);
}
