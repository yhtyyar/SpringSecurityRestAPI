package com.java.SpringSecurityRestAPI.service.impl;

import com.java.SpringSecurityRestAPI.model.Event;
import com.java.SpringSecurityRestAPI.repository.EventRepository;
import com.java.SpringSecurityRestAPI.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;


    @Override
    public Event create(Event event) {
        log.info("Create Event {}", event);
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        log.info("Update Event {}", event);
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        log.info("Get Event by ID {}", id);
        return eventRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete Event by ID {}", id);
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAll() {
        log.info("Get all Events");
        return eventRepository.findAll();
    }
}
