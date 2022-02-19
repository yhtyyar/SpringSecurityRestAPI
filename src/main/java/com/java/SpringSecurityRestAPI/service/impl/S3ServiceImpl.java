package com.java.SpringSecurityRestAPI.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.java.SpringSecurityRestAPI.model.File;
import com.java.SpringSecurityRestAPI.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private static final String BUCKET_NAME = "yhtyyartest";
    private final AmazonS3 amazonS3;


    @Override
    @SneakyThrows
    public void uploadFile(File file) {

        java.io.File newFile = new java.io.File(file.getLocation());
        amazonS3.putObject(
                BUCKET_NAME,
                file.getFileName(),
                newFile
        );
        log.info("Upload file in Amazon, File{}", file);
    }


    @Override
    public void listFile() {

        ObjectListing objects = amazonS3.listObjects(BUCKET_NAME);

        log.info("List all files ");
        for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
            log.info("File name: {}", objectSummary.getKey());
        }
    }


    @Override
    public void deleteFile(File file) {
        amazonS3.deleteObject(BUCKET_NAME, file.getFileName());
        log.info("Delete File {}", file);
    }
}
