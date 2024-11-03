package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.dging.dgingmarket.util.FileUtils;
import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.web.api.dto.common.ImageRequest;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "파일 서비스", description = "파일 관리 API 엔드포인트")
public class FileApiController {

    private final FileUploadService fileUploadService;

    @PostMapping(value = "/s3",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 업로드", description = "단일 이미지를 업로드하고 관련 정보를 반환합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    public ResponseEntity<ImageResponse> uploadS3(
            @ModelAttribute
            @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            ImageRequest request
    ) {

        request.setFileName(FileUtils.generateRandomFileNameWith(request.getImage()));
        String userFilePath = FileUtils.userFilePathFrom(BasePaths.BASE_PATH_PRODUCT, request.getFileName());

        ImageResponse response = fileUploadService.upload(request.getImage(), userFilePath, request.getType());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

