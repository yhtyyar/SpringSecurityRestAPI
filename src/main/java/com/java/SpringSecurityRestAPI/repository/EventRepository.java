package com.java.SpringSecurityRestAPI.repository;

import com.java.SpringSecurityRestAPI.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
