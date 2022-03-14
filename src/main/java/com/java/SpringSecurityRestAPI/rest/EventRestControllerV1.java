package com.java.SpringSecurityRestAPI.rest;

import com.java.SpringSecurityRestAPI.dto.EventDto;
import com.java.SpringSecurityRestAPI.model.Event;
import com.java.SpringSecurityRestAPI.service.impl.EventServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventRestControllerV1 {


    private final EventServiceImpl eventService;

    @PostMapping
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<?> create(@RequestBody @NonNull EventDto eventDto) {
        Event event = eventService.create(eventDto.toEntity());

        return new ResponseEntity<>(EventDto.fromEntity(event), HttpStatus.CREATED);
    }


    @PutMapping
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<?> update(@RequestBody @NonNull EventDto eventDto) {
        Event event = eventService.create(eventDto.toEntity());

        return new ResponseEntity<>(EventDto.fromEntity(event), HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('events:read')")
    public ResponseEntity<?> getAll() {
        List<Event> eventList = eventService.getAll();
        if (CollectionUtils.isEmpty(eventList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<EventDto> eventDtoList = eventList.stream().map(EventDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('events:read')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Event event = eventService.getById(id);
        if (Objects.isNull(event)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EventDto.fromEntity(event), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        eventService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
