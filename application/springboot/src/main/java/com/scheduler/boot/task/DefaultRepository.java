/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task;

import com.scheduler.tasks.repository.DefaultTaskRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
@Profile({"development", "development_email"})
public class DefaultRepository extends DefaultTaskRepositoryImpl{
    
}
