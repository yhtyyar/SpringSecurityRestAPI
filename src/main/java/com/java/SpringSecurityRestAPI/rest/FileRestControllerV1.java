package com.java.SpringSecurityRestAPI.rest;

import com.java.SpringSecurityRestAPI.model.File;
import com.java.SpringSecurityRestAPI.service.impl.FileServiceImpl;
import com.java.SpringSecurityRestAPI.service.impl.S3ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/files")
public class FileRestControllerV1 {


    private final FileServiceImpl fileService;
    private final S3ServiceImpl s3Service;

    @PostMapping
    @PreAuthorize("hasAuthority('files:write')")
    public File create(@RequestBody @NonNull File file) {
        s3Service.uploadFile(file);
        return fileService.create(file);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('files:write')")
    public File update(@RequestBody @NonNull File file) {
        //TODO you need to come up with an implementation of the update
        return fileService.update(file);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('files:read')")
    public List<File> getAll() {
        s3Service.listFile();
        return fileService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('files:read')")
    public File getById(@PathVariable("id") Long id) {
        // TODO need to come up with an implementation by id
        return fileService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('files:write')")
    public void deleteById(@PathVariable("id") Long id) {
        File file = fileService.getById(id);
        s3Service.deleteFile(file);

        fileService.deleteById(id);
    }
}
