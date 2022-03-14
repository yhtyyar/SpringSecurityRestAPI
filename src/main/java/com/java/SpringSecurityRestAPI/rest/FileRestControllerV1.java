package com.java.SpringSecurityRestAPI.rest;

import com.java.SpringSecurityRestAPI.dto.FileDto;
import com.java.SpringSecurityRestAPI.service.impl.FileServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileRestControllerV1 {


    private final FileServiceImpl fileService;


    @PostMapping
    @PreAuthorize("hasAuthority('files:write')")
    public ResponseEntity<?> upload(@RequestBody @NonNull FileDto fileDto) {
        fileService.upload(fileDto.toEntity());
        return ResponseEntity.ok("File Uploaded Successfully");
    }


    @PutMapping
    @PreAuthorize("hasAuthority('files:write')")
    public ResponseEntity<?> download(@RequestBody @NonNull FileDto fileDto) {
        if (StringUtils.hasText(fileDto.getFileName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is missing");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                fileDto.getFileName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource = new InputStreamResource(fileService.download(fileDto.toEntity()));

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('files:read')")
    public ResponseEntity<?> listFiles() {
        return ResponseEntity.ok(fileService.listFiles()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No file present in bucket")));
    }


    @DeleteMapping("/{fileName}")
    @PreAuthorize("hasAuthority('files:write')")
    public ResponseEntity<?> deleteFile(@PathVariable("fileName") String fileName) {
        fileService.deleteFile(fileName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
