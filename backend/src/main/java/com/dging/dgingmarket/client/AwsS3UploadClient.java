package com.dging.dgingmarket.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AwsS3UploadClient implements UploadClient {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void upload(InputStream inputStream, ObjectMetadata objectMetadata, String filePath) {
        amazonS3.putObject(new PutObjectRequest(bucket, filePath, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    @Override
    public boolean doesFileExists(String fileName) {
        return amazonS3.doesObjectExist(this.bucket, fileName);
    }

    @Override
    public void deleteFiles(List<String> fileNames) {
        fileNames.stream().filter(this::doesFileExists)
                .forEach(fileName -> amazonS3.deleteObject(new DeleteObjectRequest(this.bucket, fileName)));
    }

    @Override
    public void delete(String fileName) {
        if(doesFileExists(fileName)) {
            amazonS3.deleteObject(new DeleteObjectRequest(this.bucket, fileName));
        }
    }
}
