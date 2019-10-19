package com.scheduler.boot.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.users.User;
import com.scheduler.users.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TestNotificationSenderImpl {

    private NotificationSender sender;

    @Mock
    private User user;

    @Mock
    private EmailSender sendEmail;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sender = Mockito.spy(new NotificationSenderImpl(user, sendEmail));
    }

    @Test
    public void testNotificationSender() {

        List<TaskDTO> taskList = new ArrayList<>();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("test");

        taskList.add(taskDTO);

        long userID = 0;
        UserDTO userDTO = new UserDTO();
        Mockito.when(user.get(userID)).thenReturn(userDTO);

        sender.sendTaskNotificationToUser(taskList, userID);

        Mockito.verify(sender,
                Mockito.times(1)).
                sendTaskNotification("Task list for the next 14 days\n\nTask: test \nDescription: \nStart: \nEnd: \n\n",
                        userDTO);
    }



}
