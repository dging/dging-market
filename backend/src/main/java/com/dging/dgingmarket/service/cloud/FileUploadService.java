package com.dging.dgingmarket.service.cloud;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dging.dgingmarket.client.UploadClient;
import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.FileUtils;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static com.dging.dgingmarket.exception.io.CIOException.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileUploadService {

    private final UploadClient s3Client;
    private final ImageRepository imageRepository;

    @Transactional
    public ImageResponse upload(MultipartFile uploadFile, String filePath, ImageType type) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getSize());
        objectMetadata.setContentType(uploadFile.getContentType());

        try (InputStream inputStream = uploadFile.getInputStream()) {
            s3Client.upload(inputStream, objectMetadata, filePath);
        } catch (SdkClientException e) {
            throw new CCloudCommunicationException();
        } catch (IOException e) {
            throw new CFileConvertFailedException();
        }

        String url = s3Client.getFileUrl(filePath);

        String fileName = FileUtils.filenameFrom(filePath);
        long fileSize = uploadFile.getSize();

        User user = EntityUtils.userThrowable();

        Image imageToCreate = Image.create(user, type, fileName, filePath, url, (int) fileSize);
        Image createdImage = imageRepository.saveAndFlush(imageToCreate);

        Long id = createdImage.getId();

        return new ImageResponse(id, url);
    }

    public boolean doesFileExists(String fileName) {
        return s3Client.doesFileExists(fileName);
    }


    public void delete(String fileUrl) {
        try {
            s3Client.delete(fileUrl);
        } catch (SdkClientException e) {
            throw new CCloudCommunicationException();
        }
    }

    public void deleteFiles(List<String> fileNames) {
        try {
            s3Client.deleteFiles(fileNames);
        } catch (SdkClientException e) {
            throw new CCloudCommunicationException();
        }
    }

    //기존 확장자명 유지한 채 유니크한 파일의 이름 생성
    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    //파일 확장자명 가져옴
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new CInvalidFileFormatException();
        }
    }
}
