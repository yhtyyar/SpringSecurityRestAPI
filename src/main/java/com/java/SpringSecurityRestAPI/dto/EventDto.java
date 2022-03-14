package com.java.SpringSecurityRestAPI.dto;

import com.java.SpringSecurityRestAPI.model.Event;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class EventDto {

    private Long id;
    private UserDto user;
    private String eventName;
    private FileDto file;


    public Event toEntity() {
        return Event.builder()
                .id(this.id)
                .user(this.user.toEntity())
                .eventName(this.eventName)
                .file(this.file.toEntity())
                .build();
    }


    public static EventDto fromEntity(Event event) {

        if (Objects.isNull(event)) {
            return null;
        }

        return EventDto.builder()
                .id(event.getId())
                .user(UserDto.fromEntity(event.getUser()))
                .eventName(event.getEventName())
                .file(FileDto.fromEntity(event.getFile()))
                .build();
    }
}
