package com.java.SpringSecurityRestAPI.service;

import com.java.SpringSecurityRestAPI.model.File;

import java.util.List;

public interface FileService {

    File create(File file);

    File update(File file);

    File getById(Long id);

    void deleteById(Long id);

    List<File> getAll();
}
