package com.java.SpringSecurityRestAPI.repository;

import com.java.SpringSecurityRestAPI.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
