package com.example.demo.aws.s3test;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;

@Slf4j
public class S3Test {
    private static Region region;
    private static S3Client s3;
    private static final String BUCKET_NAME = "my-employee-errors-bucket-test-log";

    public static void init() {
        region = Region.EU_WEST_2;
        s3 = S3Client.builder().region(region).build();
        log.info("S3 Client created");
    }

    public synchronized void uploadErrorLog(String errorClass, String errorContent){
        String fileName = errorClass + System.currentTimeMillis();
        String fileContent = errorContent;

        log.info("Uploading error log " + fileName);

        s3.putObject(PutObjectRequest.builder().bucket(BUCKET_NAME).key(fileName)
                        .build(),
                RequestBody.fromString(fileContent));

        log.info("Upload complete");

    }
}
