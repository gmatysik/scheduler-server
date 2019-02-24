package com.scheduler.boot.health;

import com.scheduler.boot.notification.service.NotificationSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private NotificationSpringService notificationService;

    @RequestMapping(path = "/status", produces = "application/json")
    public Status status(){
        return new Status("ok");
    }
}
