package com.java.SpringSecurityRestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "location")
    private String location;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "time_of_creating")
    private LocalDateTime timeOfCreating;

}
