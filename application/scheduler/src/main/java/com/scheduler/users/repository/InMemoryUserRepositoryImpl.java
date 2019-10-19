package com.scheduler.users.repository;

import com.scheduler.users.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class InMemoryUserRepositoryImpl implements UserRepository {

    private List<UserDTO> users = new ArrayList<>();

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> list = new ArrayList<>();
        list.addAll(users);
        return list;
    }

    @Override
    public UserDTO get(long id) {
        UserDTO userDTO = find(id);
        return userDTO != null ? new UserDTO(userDTO) : null;
    }

    @Override
    public UserDTO create(UserDTO object) {
        UserDTO user = new UserDTO();
        user.setPhone(object.getPhone());
        user.setEmail(object.getEmail());
        user.setId(new Random().nextInt(100));
        users.add(user);
        return new UserDTO(user);
    }

    @Override
    public UserDTO update(UserDTO object) {
        UserDTO user = find(object.getId());
        user.setEmail(object.getEmail());
        user.setPhone(object.getPhone());
        return user;
    }

    @Override
    public void remove(long id) {
        users.removeIf(element -> element.getId() == id);
    }

    @Override
    public UserDTO find(UserDTO search) {
        return null;
    }

    private UserDTO find(long id){
        Optional<UserDTO> userFound = users.stream().filter(element -> element.getId() == id).findFirst();
        return userFound.isPresent() ? userFound.get() : null;
    }
}
