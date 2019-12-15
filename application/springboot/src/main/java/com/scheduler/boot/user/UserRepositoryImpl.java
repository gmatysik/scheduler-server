package com.scheduler.boot.user;

import com.scheduler.boot.task.TaskSpringRepository;
import com.scheduler.boot.task.TaskTable;
import com.scheduler.users.UserDTO;
import com.scheduler.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class UserRepositoryImpl implements UserRepository {


    private UserSpringRepository userRepository;

    @Autowired
    public UserRepositoryImpl(UserSpringRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {

        //List<UserDTO> userDTOS = new ArrayList<>();

        Iterable<UserTable> source =  userRepository.findAll();


        Stream<UserTable> targetStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(source.iterator(), Spliterator.ORDERED),
                false);

        List<UserDTO> userDTOS = targetStream.map(user -> user.getDTOObject()).collect(Collectors.toList());

source.forEach(user -> user.getDTOObject());
        //targetStream.

        return userDTOS;
    }

    @Override
    public UserDTO get(long id) {
        return null;
    }

    @Override
    public UserDTO create(UserDTO object) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO object) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public UserDTO find(UserDTO search) {
        return null;
    }
}
