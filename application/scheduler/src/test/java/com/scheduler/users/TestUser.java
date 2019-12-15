package com.scheduler.users;

import com.scheduler.tasks.validation.TaskValidationException;
import com.scheduler.users.repository.InMemoryUserRepositoryImpl;
import com.scheduler.users.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUser extends TestCase {

    private UserDTO newUserDto;
    private UserDTO createdUserDto;
    private UserRepository repository;
    private User user;

    @Before
    public void setUp() {
        repository = new InMemoryUserRepositoryImpl();

        newUserDto = new UserDTO();
        newUserDto.setEmail("aaa@aa.aa");
        newUserDto.setPhone("1234");


        createdUserDto = new UserDTO();
        createdUserDto.setEmail(newUserDto.getEmail());
        createdUserDto.setPhone(newUserDto.getPhone());

        user = new UserImpl(repository);
    }

    @Test
    public void testAddUser() {

        try {

            UserDTO userdto = user.add(newUserDto);

            assertNotNull(userdto);
            assertNotNull(userdto.getId());
            assertNotSame(newUserDto.getId(), userdto.getId());
            assertNotNull(userdto.getId());
            assertEquals(newUserDto.getEmail(), userdto.getEmail());
            assertEquals(newUserDto.getPhone(), userdto.getPhone());

        } catch (TaskValidationException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetUser() {

        try {
            UserDTO createdDTO = user.add(newUserDto);
            UserDTO foundUser = user.get(createdDTO.getId());
            assertEquals(createdDTO.getId(), foundUser.getId());
            assertEquals(createdDTO.getEmail(), foundUser.getEmail());
            assertEquals(createdDTO.getPhone(), foundUser.getPhone());

        } catch (TaskValidationException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetUserNotFound() {

        try {
            UserDTO createdDTO = user.add(newUserDto);
            UserDTO foundUser = user.get(createdDTO.getId() + 1);

            assertNull(foundUser);
        } catch (TaskValidationException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetAll() throws TaskValidationException {
        UserDTO user1 = new UserDTO();
        user1.setPhone("1234");
        user1.setEmail("aaaaaa@aa.aa");

        UserDTO user2 = new UserDTO();
        user2.setPhone("2222");
        user2.setEmail("bb@bb.bb");

        UserDTO user1_1 = user.add(user1);
        UserDTO user1_2 = user.add(user2);

        List<UserDTO> users = user.getAll();
        assertEquals(2, users.size());
        assertEquals(users.get(0).getId(), user1_1.getId());
        assertEquals(users.get(1).getId(), user1_2.getId());

    }


    @Test
    public void testGet() throws TaskValidationException {
        UserDTO user1 = new UserDTO();
        user1.setPhone("1234");
        user1.setEmail("aaaaaa@aa.aa");

        UserDTO user2 = new UserDTO();
        user2.setPhone("2222");
        user2.setEmail("bb@bb.bb");

        UserDTO user1_1 = user.add(user1);
        UserDTO user1_2 = user.add(user2);

        UserDTO userDTO = user.get(user1_1.getId());
        assertEquals(userDTO.getId(), user1_1.getId());

        userDTO = user.get(user1_2.getId());
        assertEquals(userDTO.getId(), user1_2.getId());

    }

    @Test
    public void testUpdate() throws TaskValidationException {
        UserDTO user1 = new UserDTO();
        user1.setPhone("1234");
        user1.setEmail("aaaaaa@aa.aa");

        UserDTO user2 = new UserDTO();
        user2.setPhone("2222");
        user2.setEmail("bb@bb.bb");

        user.add(user1);
        UserDTO user1_2 = user.add(user2);

        user1_2.setEmail("eeee@ee.ee");

        UserDTO userup = user.update(user1_2);
        assertNotNull(userup);
        assertEquals(userup.getEmail(), user1_2.getEmail());
        assertEquals(userup.getPhone(), user1_2.getPhone());

        UserDTO userUpdated = user.get(user1_2.getId());
        assertEquals(userup.getEmail(), userUpdated.getEmail());
        assertEquals(userup.getPhone(), userUpdated.getPhone());

    }

    @Test
    public void testRemove() throws TaskValidationException {
        UserDTO user1 = new UserDTO();
        user1.setPhone("1234");
        user1.setEmail("aaaaaa@aa.aa");

        UserDTO user2 = new UserDTO();
        user2.setPhone("2222");
        user2.setEmail("bb@bb.bb");

        UserDTO user1_1 = user.add(user1);
        UserDTO user1_2 = user.add(user2);
        assertEquals(2, user.getAll().size());
        assertNotNull(user.get(user1_1.getId()));

        user.remove(user1_1.getId());

        assertNull(user.get(user1_1.getId()));
        assertEquals(1, user.getAll().size());
        assertEquals(user1_2.getId(), user.getAll().get(0).getId());
    }
}
