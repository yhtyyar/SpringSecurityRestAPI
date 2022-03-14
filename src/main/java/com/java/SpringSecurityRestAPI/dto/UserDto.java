package com.java.SpringSecurityRestAPI.dto;

import com.java.SpringSecurityRestAPI.model.Event;
import com.java.SpringSecurityRestAPI.model.Role;
import com.java.SpringSecurityRestAPI.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {


    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private List<EventDto> eventList;


    public User toEntity() {

        List<Event> events = CollectionUtils.isEmpty(this.eventList) ?
                Collections.emptyList() :
                this.eventList.stream().map(EventDto::toEntity).collect(Collectors.toList());

        return User.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .role(this.role)
                .eventList(events)
                .build();
    }

    public static UserDto fromEntity(User user) {

        if (Objects.isNull(user)) {
            return null;
        }

        List<EventDto> eventDtoList = CollectionUtils.isEmpty(user.getEventList()) ?
                Collections.emptyList() :
                user.getEventList().stream().map(EventDto::fromEntity).collect(Collectors.toList());

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .eventList(eventDtoList)
                .build();
    }
}
