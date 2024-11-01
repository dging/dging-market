package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.dging.dgingmarket.util.FileUtils;
import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.web.api.dto.common.ImageRequest;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;

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

