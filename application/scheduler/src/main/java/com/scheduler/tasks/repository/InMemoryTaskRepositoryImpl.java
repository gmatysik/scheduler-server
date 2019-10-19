/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks.repository;

import com.scheduler.tasks.TaskDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;

/**
 * Dummy implementation of task repository.
 * For test purposes only as data are not stored permanently.
 * 
 * @author Grzegorz
 */
public class InMemoryTaskRepositoryImpl implements TaskRepository{

    private final static Logger LOGGER = LogManager.getLogger(InMemoryTaskRepositoryImpl.class);
    
    private long id = 0;
    
    private final List<TaskDTO> taskList = new ArrayList<>();

    @Override
    public List<TaskDTO> getAll() {
        List<TaskDTO> list = new ArrayList<>();
        list.addAll(taskList);
        return list;
    }

    @Override
    public TaskDTO getTask(long id) {
        TaskDTO taskDTO = taskList.stream().filter(element -> element.getId() == id).findFirst().get();
        return taskDTO != null ? new TaskDTO(taskDTO) : null;
    }

    @Override
    public TaskDTO createTask(TaskDTO task) {
        task.setId(id++);
        taskList.add(task);
        return new TaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        TaskDTO updatedTask = taskList.stream().filter(element -> element.getId() == task.getId()).findFirst().get();
        updatedTask.setDescription(task.getDescription());
        updatedTask.setEnd(task.getEnd());
        updatedTask.setStart(task.getStart());
        updatedTask.setTitle(task.getTitle());
        return updatedTask;
    }

    @Override
    public void removeTask(long taskId) {
        taskList.removeIf(element -> element.getId() == taskId);
    }

    @Override
    public List<TaskDTO> findNextTasksForUserForNextNDays(long userId, int days) {
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date inNDays = cal.getTime();
        Date now = new Date();

        return taskList.stream().filter(element -> {
                if(element.getUserId() != userId){
                    return false;
                }
                Date date = null;
                try {
                    date = dt.parse(element.getStart());
                } catch (ParseException ex) {
                    LOGGER.error("Parse exception: ", ex);
                }
                if(date != null){
                    return date.before(inNDays) && date.after(now);
                }                
                return false;
            }).collect(Collectors.toList());
    }    
}