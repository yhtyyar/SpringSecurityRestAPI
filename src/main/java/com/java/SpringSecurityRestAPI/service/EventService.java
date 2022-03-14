package com.java.SpringSecurityRestAPI.service;

import com.java.SpringSecurityRestAPI.model.Event;

import java.util.List;

public interface EventService {

    Event create(Event event);
    Event update(Event event);
    Event getById(Long id);
    void deleteById(Long id);
    List<Event> getAll();
}
