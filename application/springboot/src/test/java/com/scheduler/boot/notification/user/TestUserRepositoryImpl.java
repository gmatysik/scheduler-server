package com.scheduler.boot.notification.user;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.users.UserDTO;
import com.scheduler.users.repository.InMemoryUserRepositoryImpl;
import com.scheduler.users.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestUserRepositoryImpl extends TestCase {

    public static final String PHONE = "1234";
    public static final String EMAIL = "email@test.com";
    private NotificationSender sender;

    private UserRepository userRepository;

    private UserDTO userDTO;

    @Before
    public void setUp(){
        userRepository = new InMemoryUserRepositoryImpl();
        userDTO = new UserDTO();
        userDTO.setPhone(PHONE);
        userDTO.setEmail(EMAIL);

    }

    @Test
    public void testRepo(){
        UserDTO user = userRepository.create(userDTO);

        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertNotNull(user.getId());
        assertEquals(1, userRepository.getAll().size());
        assertEquals(user.getId(), userRepository.get(user.getId()).getId());
    }
}
