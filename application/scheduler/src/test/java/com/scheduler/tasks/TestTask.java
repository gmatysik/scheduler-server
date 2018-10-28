/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    
    private TaskRepository taskRepository;
    
    @Before
    public void setUp(){
        taskRepository = new DefaultTaskRepositoryImpl();
        task = TaskServiceFactory.createTaskService(taskRepository);
        initializeTestData();
    }

    private void initializeTestData() {
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
       
        taskDTO1 = createTask(startDate, null, "Title 1", "Description 1" );
        taskDTO2 = createTask(startDatePlus1, null, "Title 2", "Description 2" );
        taskDTO3 = createTask(startDatePlus8, null, "Title 3", "Description 3" );
        taskDTO4 = createTask(startDateMinus1, null, "Title 4", "Description 4" );
       
        try {
            task.addTask(taskDTO1);
            task.addTask(taskDTO2);
            task.addTask(taskDTO3);
            task.addTask(taskDTO4);
        } catch (TaskValidationException ex) {
            fail("");
        }
    }

    private TaskDTO createTask(String startDate, String endDate, String title, String description) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setStart(startDate);
        taskDTO.setEnd(endDate);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
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
        
        TaskDTO taskDTO = createTask(startDate, endDate, title, description);
        try{            
            taskDTO = task.addTask(taskDTO);            
        } catch(TaskValidationException e){
            fail("Should not have been thrown");
        }
        assertEquals(4, taskDTO.getId());
        
        TaskDTO addedTask = task.getTask(4);
        assertEquals(4, addedTask.getId());
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
        
        TaskDTO taskDTO = createTask(startDate, endDate, title, description);
        try{            
            task.addTask(taskDTO);
            fail("Exceptin should have been thrown");
        } catch(TaskValidationException e){
            assertEquals("title.not.empty", e.getMessages().get(0));
        }
        
    }
    
    @Test
    public void testUpdateTask() throws TaskValidationException{
        TaskDTO oldTaskDTO = task.getTask(0);
        
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
        
        TaskDTO findTaskDTO = task.getTask(0);
        assertEquals(findTaskDTO.getId(), updatedTask.getId());
        assertEquals(findTaskDTO.getStart(), updatedTask.getStart());
        assertEquals(findTaskDTO.getEnd(), updatedTask.getEnd());
        assertEquals(findTaskDTO.getTitle(), updatedTask.getTitle());
        assertEquals(findTaskDTO.getDescription(), updatedTask.getDescription());
        
    }

    @Test
    public void testUpdateTask_ValidationException(){
        TaskDTO oldTaskDTO = task.getTask(0);
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
        List<TaskDTO> taskList = taskRepository.getAll();
        assertEquals(4, taskList.size());
    }

    @Test
    public void testFindNextSevenDaysTasksForUser(){        
        List<TaskDTO> taskList = taskRepository.findNextSevenDaysTasksForUser(0);
        assertEquals(2, taskList.size());
    }
    
    @Test
    public void testRemoveTask(){        
        taskRepository.removeTask(1);
        assertEquals(3, taskRepository.getAll().size());
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
