package com.scheduler.users;

import junit.framework.TestCase;

import java.util.Random;

public class TestUserDTO extends TestCase {

    public void testCopyConstructor(){
        UserDTO user = new UserDTO(new Random().nextInt(100), "aaa@qq.aa", "123456");

        UserDTO user2 = new UserDTO(user);

        assertEquals(user.getId(), user2.getId());
        assertEquals(user.getEmail(), user2.getEmail());
        assertEquals(user.getPhone(), user2.getPhone());
    }


}
