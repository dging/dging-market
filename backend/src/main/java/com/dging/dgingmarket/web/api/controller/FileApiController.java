package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.dging.dgingmarket.util.FileUtils;
import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.web.api.dto.common.ImageRequest;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileApiController {

    private final FileUploadService fileUploadService;

    @PostMapping("/s3")
    public ResponseEntity<ImageResponse> uploadS3(@Valid ImageRequest request) {

        MultipartFile image = request.getImage();

        request.setFileName(FileUtils.generateRandomFileNameWith(image));
        String userFilePath = FileUtils.userFilePathFrom(BasePaths.BASE_PATH_PRODUCT, request.getFileName());

        ImageResponse response = fileUploadService.upload(image, userFilePath, request.getType());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

