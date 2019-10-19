/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import com.scheduler.tasks.repository.InMemoryTaskRepositoryImpl;
import com.scheduler.tasks.repository.TaskRepository;
import com.scheduler.tasks.validation.TaskValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;

import com.scheduler.users.User;
import com.scheduler.users.UserDTO;
import com.scheduler.users.UserImpl;
import com.scheduler.users.repository.InMemoryUserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Grzegorz
 */
public class TestTask {
    
    private Task task;
    private TaskDTO taskDTO1;
    private TaskDTO taskDTO2;
    private TaskDTO taskDTO3;
    private TaskDTO taskDTO4;
    private TaskDTO taskDTO5;
    
    private TaskRepository taskRepository;
    private long userId;

    User user = new UserImpl(new InMemoryUserRepositoryImpl());

    @Before
    public void setUp(){
        taskRepository = new InMemoryTaskRepositoryImpl();
        task = TaskFactory.createTask(taskRepository);
        initializeTestData();
    }

    private void initializeTestData() {
        userId = new Random().nextLong();

        createTasksForUserId(userId);
    }

    private void createTasksForUserId(long userId) {
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 1);
        String startDate = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String startDatePlus1 = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 8);
        String startDatePlus8 = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String startDateMinus1 = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 4);
        String startDatePlus4 = dt.format(cal.getTime());

        taskDTO1 = createTask(startDate, null, "Title 1", "Description 1", userId);
        taskDTO2 = createTask(startDatePlus8, null, "Title 2", "Description 2", userId);
        taskDTO3 = createTask(startDatePlus4, null, "Title 3", "Description 3", userId);
        taskDTO4 = createTask(startDateMinus1, null, "Title 4", "Description 4", userId);
        taskDTO5 = createTask(startDatePlus1, null, "Title 5", "Description 5", userId);

        try {
            task.addTask(taskDTO1);
            task.addTask(taskDTO2);
            task.addTask(taskDTO3);
            task.addTask(taskDTO4);
            task.addTask(taskDTO5);
        } catch (TaskValidationException ex) {
            fail("");
        }
    }

    private TaskDTO createTask(String startDate, String endDate, String title, String description, long userId) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setStart(startDate);
        taskDTO.setEnd(endDate);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setUserId(userId);
        return taskDTO;
    }
    
    @Test
    public void testCreateTask(){
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        String startDate = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 35);
        String endDate = dt.format(cal.getTime());

        String title = "Title 5";
        String description = "Description 5";
        
        TaskDTO taskDTO = createTask(startDate, endDate, title, description, userId);
        try{            
            taskDTO = task.addTask(taskDTO);            
        } catch(TaskValidationException e){
            fail("Should not have been thrown");
        }
        assertEquals(5, taskDTO.getId());
        
        TaskDTO addedTask = task.get(5);
        assertEquals(5, addedTask.getId());
        assertEquals(startDate, addedTask.getStart());
        assertEquals(endDate, addedTask.getEnd());
        assertEquals(title, addedTask.getTitle());
        assertEquals(description, addedTask.getDescription());
    }

    @Test
    public void testCreateTask_ValidationException(){
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        String startDate = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 35);
        String endDate = dt.format(cal.getTime());

        String title = null;
        String description = "Description 5";
        
        TaskDTO taskDTO = createTask(startDate, endDate, title, description, userId);
        try{            
            task.addTask(taskDTO);
            fail("Exceptin should have been thrown");
        } catch(TaskValidationException e){
            assertEquals("title.not.empty", e.getMessages().get(0));
        }
        
    }
    
    @Test
    public void testUpdateTask() throws TaskValidationException{
        TaskDTO oldTaskDTO = task.get(0);
        
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        String newStartDate = dt.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 35);
        String newEndDate = dt.format(cal.getTime());

        String newTitle = "new title";
        String newDescription = "new description";
        long newUserId = 1;
        
        TaskDTO updateDTO = new TaskDTO(oldTaskDTO);
        updateDTO.setEnd(newEndDate);
        updateDTO.setStart(newStartDate);
        updateDTO.setTitle(newTitle);
        updateDTO.setDescription(newDescription);
        updateDTO.setUserId(newUserId);
        TaskDTO updatedTask = task.updateTask(updateDTO);
        
        assertEquals(updatedTask.getId(), updateDTO.getId());
        assertEquals(updatedTask.getStart(), updateDTO.getStart());
        assertEquals(updatedTask.getEnd(), updateDTO.getEnd());
        assertEquals(updatedTask.getTitle(), updateDTO.getTitle());
        assertEquals(updatedTask.getDescription(), updateDTO.getDescription());
        
        assertEquals(updatedTask.getId(), oldTaskDTO.getId());
        assertNotEquals(updatedTask.getStart(), oldTaskDTO.getStart());
        assertNotEquals(updatedTask.getEnd(), oldTaskDTO.getEnd());
        assertNotEquals(updatedTask.getTitle(), oldTaskDTO.getTitle());
        assertNotEquals(updatedTask.getDescription(), oldTaskDTO.getDescription());
        
        TaskDTO findTaskDTO = task.get(0);
        assertEquals(findTaskDTO.getId(), updatedTask.getId());
        assertEquals(findTaskDTO.getStart(), updatedTask.getStart());
        assertEquals(findTaskDTO.getEnd(), updatedTask.getEnd());
        assertEquals(findTaskDTO.getTitle(), updatedTask.getTitle());
        assertEquals(findTaskDTO.getDescription(), updatedTask.getDescription());
        
    }

    @Test
    public void testUpdateTask_ValidationException(){
        TaskDTO oldTaskDTO = task.get(0);
        TaskDTO updateDTO = new TaskDTO(oldTaskDTO);
        updateDTO.setEnd(null);
        updateDTO.setStart(null);
        updateDTO.setTitle(null);
        updateDTO.setDescription(null);
        try {
            task.updateTask(updateDTO);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(2, ex.getMessages().size());
            assertTrue(ex.getMessages().contains("title.not.empty"));
            assertTrue(ex.getMessages().contains("start.not.empty"));
        }
                
    }
    
    @Test
    public void testGetAllTasks(){        
        List<TaskDTO> taskList = task.getAllTasks();
        assertEquals(5, taskList.size());
    }

    @Test
    public void testFindNextSevenDaysTasksForUser() throws ParseException, TaskValidationException {
        User user = new UserImpl(new InMemoryUserRepositoryImpl());
        UserDTO userDTO = new UserDTO(-1, "aaa@ss.dd", "4567");
        userDTO = user.add(userDTO);

        List<TaskDTO> allTasks = task.getAllTasks();
        for(TaskDTO taskdto : allTasks){
            task.removeTask(taskdto.getId());
        }

        createTasksForUserId(userDTO.getId());

        SimpleDateFormat dateFormat = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        List<TaskDTO> taskList = task.getTasksFromNextSevenDaysForUser(userDTO.getId());
        assertEquals(4, taskList.size());
        
        Date date1 = dateFormat.parse(taskList.get(0).getStart());
        Date date2 = dateFormat.parse(taskList.get(1).getStart());
        Date date3 = dateFormat.parse(taskList.get(2).getStart());
        Date date4 = dateFormat.parse(taskList.get(3).getStart());
                
        assertTrue(date1.before(date2));
        assertTrue(date2.before(date3));
        assertTrue(date3.before(date4));
    }
    
    @Test
    public void testRemoveTask(){        
        taskRepository.removeTask(1);
        assertEquals(4, taskRepository.getAll().size());
    }

    @Test
    public void testGetTask(){        
        TaskDTO taskDTO = taskRepository.getTask(1);
        assertNotNull(taskDTO);
        checkIfTasksEquals(taskDTO, taskDTO2);
    }

    private void checkIfTasksEquals(TaskDTO taskDTO, TaskDTO _taskDTO) {
        assertEquals(taskDTO.getId(), _taskDTO.getId());
        assertEquals(taskDTO.getDescription(), _taskDTO.getDescription());        
        assertEquals(taskDTO.getEnd(), _taskDTO.getEnd());
        assertEquals(taskDTO.getStart(), _taskDTO.getStart());
        assertEquals(taskDTO.getTitle(), _taskDTO.getTitle());
        assertEquals(taskDTO.getUserId(), _taskDTO.getUserId());
    }

}
