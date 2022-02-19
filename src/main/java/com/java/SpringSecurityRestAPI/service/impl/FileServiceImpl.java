package com.java.SpringSecurityRestAPI.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.java.SpringSecurityRestAPI.model.File;
import com.java.SpringSecurityRestAPI.repository.FileRepository;
import com.java.SpringSecurityRestAPI.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final FileRepository fileRepository;
    private final AmazonS3 amazonS3;


    @Override
    public File create(File file) {
        log.info("Create File {}", file);

        return fileRepository.save(file);
    }

    @Override
    public File update(File file) {
        log.info("Update File {}", file);
        return fileRepository.save(file);
    }

    @Override
    public File getById(Long id) {
        log.info("Get File by Id {}", id);
        return fileRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete File by Id {}", id);
        fileRepository.deleteById(id);
    }

    @Override
    public List<File> getAll() {
        log.info("Get all files");
        return fileRepository.findAll();
    }
}
