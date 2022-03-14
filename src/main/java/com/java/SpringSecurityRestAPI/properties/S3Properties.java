package com.java.SpringSecurityRestAPI.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3Properties {

    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
