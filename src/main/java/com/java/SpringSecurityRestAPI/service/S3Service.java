package com.java.SpringSecurityRestAPI.service;


import com.java.SpringSecurityRestAPI.model.File;

public interface S3Service {

    void uploadFile(File file);

    void listFile();

    void deleteFile(File file);
}
