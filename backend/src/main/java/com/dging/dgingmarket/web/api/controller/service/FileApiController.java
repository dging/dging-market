package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.exception.ApiErrorCodeExample;
import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.dging.dgingmarket.util.FileUtils;
import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.web.api.dto.common.ImageCreateRequest;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dging.dgingmarket.exception.CommonErrorCode.*;
import static com.dging.dgingmarket.exception.UserErrorCode._USER_NOT_FOUND;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "0. 파일", description = "파일 API 엔드포인트")
public class FileApiController {

    private final FileUploadService fileUploadService;

    @PostMapping(value = "/s3",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 업로드", description = "단일 이미지를 업로드 후 관련 정보를 반환합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _INVALID_FILE,
            _CLOUD_COMMUNICATION_ERROR,
            _FILE_CONVERT_FAILED,
    })
    public ResponseEntity<ImagesResponse> uploadS3(
            @ModelAttribute
            @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            ImageCreateRequest request
    ) {

        request.setFileName(FileUtils.generateRandomFileNameWith(request.getImage()));
        String userFilePath = FileUtils.userFilePathFrom(BasePaths.BASE_PATH_PRODUCT, request.getFileName());

        ImagesResponse response = fileUploadService.upload(request.getImage(), userFilePath, request.getType());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/s3/{id}")
    @Operation(summary = "이미지 상세 조회", description = "단일 이미지 상세를 조회합니다..")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _IMAGE_NOT_FOUND,
    })
    public ResponseEntity<ImageResponse> fetchS3Image(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        ImageResponse response = fileUploadService.image(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

