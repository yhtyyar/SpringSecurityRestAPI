package com.java.SpringSecurityRestAPI.service.impl;

import com.java.SpringSecurityRestAPI.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventServiceImplTest {

    @Autowired
    private EventServiceImpl eventService;

    @Test
    public void  create(Event event) {
        
    }
}
