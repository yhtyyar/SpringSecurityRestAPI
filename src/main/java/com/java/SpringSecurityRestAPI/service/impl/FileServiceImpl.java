package com.java.SpringSecurityRestAPI.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.java.SpringSecurityRestAPI.model.File;
import com.java.SpringSecurityRestAPI.properties.S3Properties;
import com.java.SpringSecurityRestAPI.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;


    @Override
    public void upload(File file) {

        java.io.File newFile = new java.io.File(file.getLocation());
        file.setTimeOfCreating(LocalDateTime.now());
        log.info("File Uploaded {}", file.getFileName());

        try{
            amazonS3.putObject(s3Properties.getBucketName(), file.getFileName(), newFile);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()),
                    "Request processing failed at cloud platform", e);
        } catch (SdkClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to process your request", e);
        }
    }


    @Override
    public InputStream download(File file) {

        if (amazonS3.doesObjectExist(s3Properties.getBucketName(), file.getFileName())) {
            S3Object s3Object = amazonS3.getObject(s3Properties.getBucketName(), file.getFileName());
            log.info("File download {}", file.getFileName());
            return s3Object.getObjectContent();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Requested file does not exist on bucket");
        }
    }


    @Override
    public Optional<String> listFiles() {
        ObjectListing objectListing = amazonS3.listObjects(s3Properties.getBucketName());

        if (objectListing != null) {
            log.info("All files in the bucket");
            return Optional.of(String.valueOf(objectListing.getObjectSummaries()));
        } else {
            log.info("No file present in bucket");
            return Optional.empty();
        }
    }


    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(s3Properties.getBucketName(), fileName);
        log.info("Deleting a File {}", fileName);
    }
}
