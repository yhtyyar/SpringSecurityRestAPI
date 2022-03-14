package com.java.SpringSecurityRestAPI.dto;

import com.java.SpringSecurityRestAPI.model.File;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
public class FileDto {


    private Long id;
    private EventDto event;
    private String location;
    private String fileName;
    private LocalDateTime timeOfCreating;


    public File toEntity() {
        return File.builder()
                .id(this.id)
                .event(this.event.toEntity())
                .location(this.location)
                .fileName(this.fileName)
                .timeOfCreating(this.timeOfCreating)
                .build();
    }

    public static FileDto fromEntity(File file) {

        if (Objects.isNull(file)) {
            return null;
        }

        return FileDto.builder()
                .id(file.getId())
                .event(EventDto.fromEntity(file.getEvent()))
                .fileName(file.getFileName())
                .timeOfCreating(file.getTimeOfCreating())
                .build();
    }
}
