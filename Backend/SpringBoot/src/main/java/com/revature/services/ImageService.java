package com.revature.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.revature.config.Configuration;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Zimi Li
 */

@Service("ImageService")
public class ImageService {
    private final AmazonS3 s3Client;

    public ImageService() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(Configuration.ACCESS_KEY, Configuration.SECRET_KEY);
        s3Client = AmazonS3ClientBuilder.standard().withRegion(Configuration.REGION).
                withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    public String upload(InputStream inputStream, long size) {
        String filename = UUID.randomUUID().toString();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        s3Client.putObject(Configuration.BUCKET_NAME, Configuration.FOLDER_NAME + filename, inputStream, metadata);
        return Configuration.S3_URL + Configuration.FOLDER_NAME + filename;
    }

}
