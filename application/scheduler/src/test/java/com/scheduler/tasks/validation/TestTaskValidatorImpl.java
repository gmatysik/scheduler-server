/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks.validation;

import com.scheduler.tasks.TaskDTO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Grzegorz
 */
public class TestTaskValidatorImpl {
    
    private TaskValidator validator;
    private TaskDTO task;
    
    @Before
    public void setUp(){
        validator = new TaskValidatorImpl();
        task = new TaskDTO();
        
        SimpleDateFormat dt = new SimpleDateFormat(TaskDTO.DATE_FORMAT);

        Calendar cal = Calendar.getInstance();
        String startDate = dt.format(cal.getTime());

        cal.add(Calendar.HOUR_OF_DAY, 1);
        String endDate = dt.format(cal.getTime());
        
        task.setTitle("task title");
        task.setStart(startDate);
        task.setEnd(endDate);
    }
    
    @Test
    public void testTitleEmpty(){
        try {
            task.setTitle("");
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("title.not.empty", ex.getMessages().get(0));
        }
    }

    @Test
    public void testTitleNull(){
        try {
            task.setTitle(null);
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("title.not.empty", ex.getMessages().get(0));
        }
    }

    @Test
    public void testStartEmpty(){
        try {
            task.setStart("");
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("start.not.empty", ex.getMessages().get(0));
        }
    }

    @Test
    public void testStartNull(){
        try {
            task.setStart(null);
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("start.not.empty", ex.getMessages().get(0));
        }
    }
   
    @Test
    public void testStartPatternFalse(){
        try {
            task.setStart("11-aa-3333T");
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("start.datetime.format", ex.getMessages().get(0));
        }
    }

    @Test
    public void testEndPatternFalse(){
        try {
            task.setEnd("2018-11-22 11:aa");
            validator.validate(task);
            fail("Exception should have been thrown");
        } catch (TaskValidationException ex) {
            assertEquals(1, ex.getMessages().size());
            assertEquals("end.datetime.format", ex.getMessages().get(0));
        }
    }

    @Test
    public void testEndPatternEmpty(){
        try {
            task.setEnd("");
            validator.validate(task);
            assertTrue(true);
        } catch (TaskValidationException ex) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testEndPatternNull(){
        try {
            task.setEnd(null);
            validator.validate(task);
            assertTrue(true);
        } catch (TaskValidationException ex) {
            fail("Exception should not have been thrown");
        }
    }
    
}
