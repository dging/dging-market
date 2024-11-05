package com.dging.dgingmarket.config;

import com.dging.dgingmarket.exception.ApiErrorCodeExample;
import com.dging.dgingmarket.exception.BaseErrorCode;
import com.dging.dgingmarket.exception.ErrorReason;
import com.dging.dgingmarket.exception.ExampleHolder;
import com.dging.dgingmarket.web.api.dto.common.ErrorResponse;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

//Swagger UI에 Authorize 버튼을 추가함
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfiguration {

    /*@Bean
    RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/open*", new ClassPathResource("static/"));
    }*/

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("디깅마켓 API")
                .version("v0.0.1")
                .description("JUnit5 테스트 기반으로 자동 생성된 API 테스트 페이지 입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info)
                //Security 설정을 통해 요청 Header에 값을 담아 보내도록 설정함
                .security(List.of(
                        new SecurityRequirement().addList("Bearer Authentication")
                ));
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            ApiErrorCodeExample apiErrorCodeExample = handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);

            if (apiErrorCodeExample != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExample.value());
            }

            return operation;
        };
    }

    private void generateErrorCodeResponseExample(Operation operation, Class<? extends BaseErrorCode> type) {
        ApiResponses responses = operation.getResponses();
        // 해당 이넘에 선언된 에러코드들의 목록을 가져옵니다.
        BaseErrorCode[] errorCodes = type.getEnumConstants();
        // 400, 401, 404 등 에러코드의 상태코드들로 리스트로 모읍니다.
        // 400 같은 상태코드에 여러 에러코드들이 있을 수 있습니다.
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(errorCodes)
                        .map(
                                errorCode -> {
                                    ErrorReason errorReason = errorCode.getErrorReason();
                                    try {
                                        return ExampleHolder.builder()
                                                .holder(getSwaggerExample(errorCode.getErrorExplanation(), errorCode))
                                                .code(errorReason.getStatus())
                                                .name(errorReason.getCode())
                                                .build();
                                    } catch (NoSuchFieldException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(groupingBy(ExampleHolder::getCode));
        // response 객체들을 responses 에 넣습니다.
        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    private Example getSwaggerExample(String value, BaseErrorCode errorCode) {
        //ErrorResponse 는 클라이언트한 실제 응답하는 공통 에러 응답 객체입니다.
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        Example example = new Example();
        example.description(value);
        example.setValue(errorResponse);
        return example;
    }

    private void addExamplesToResponses(ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    // 상태 코드마다 ApiResponse을 생성합니다.
                    ApiResponse apiResponse = new ApiResponse();
                    //  List<ExampleHolder> 를 순회하며, mediaType 객체에 예시값을 추가합니다.
                    v.forEach(
                            exampleHolder -> mediaType.addExamples(
                                    exampleHolder.getName(), exampleHolder.getHolder()));
                    // ApiResponse 의 content 에 mediaType을 추가합니다.
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    // 상태코드를 key 값으로 responses 에 추가합니다.
                    responses.addApiResponse(status.toString(), apiResponse);
                });
    }
}
