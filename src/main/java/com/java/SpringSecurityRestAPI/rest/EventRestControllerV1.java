package com.java.SpringSecurityRestAPI.rest;

import com.java.SpringSecurityRestAPI.model.Event;
import com.java.SpringSecurityRestAPI.service.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
public class EventRestControllerV1 {


    private final EventServiceImpl eventService;

    @PostMapping
    @PreAuthorize("hasAuthority('events:write')")
    public Event create(@RequestBody @NonNull Event event) {
        return  eventService.create(event);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('events:write')")
    public Event update(@RequestBody @NonNull Event event) {
        return eventService.update(event);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('events:read')")
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('events:read')")
    public Event getById(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('events:write')")
    public void delete(@PathVariable("id") Long id) {
        eventService.deleteById(id);
    }
}
