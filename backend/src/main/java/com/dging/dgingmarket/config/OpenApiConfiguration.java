package com.dging.dgingmarket.config;

import com.dging.dgingmarket.docs.CustomDescriptionOverride;
import com.dging.dgingmarket.exception.*;
import com.dging.dgingmarket.util.JsonUtils;
import com.dging.dgingmarket.util.annotation.CustomPageableParameter;
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
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.reflections.Reflections;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Field;
import java.util.*;

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
public class OpenApiConfiguration implements WebMvcConfigurer {

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

            //메서드 커스터마이징

            //ApiErrorCodeClassExample - 에러코드 기본값을 클래스 레벨에서 문서화
            ApiErrorCodeClassExample apiErrorCodeClassExample = handlerMethod.getMethodAnnotation(ApiErrorCodeClassExample.class);

            if (apiErrorCodeClassExample != null) {
                // 해당 이넘에 선언된 에러코드들의 목록을 가져옵니다.
                BaseErrorCode[] errorCodes = apiErrorCodeClassExample.value().getEnumConstants();
                generateErrorCodeResponseExample(operation.getResponses(), errorCodes);
            }

            //ApiErrorCodeExample - 에러코드 기본값을 코드 배열을 기준으로 문서화
            ApiErrorCodeExample apiErrorCodeExample = handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);

            if (apiErrorCodeExample != null) {

                Reflections reflections = new Reflections(BaseErrorCode.class.getPackageName());
                Set<Class<? extends BaseErrorCode>> errorCodeClasses = reflections.getSubTypesOf(BaseErrorCode.class);

                BaseErrorCode[] errorCodes = Arrays.stream(apiErrorCodeExample.value()).flatMap(code -> {
                    // 구현체 인스턴스 생성 후 메서드 호출
                    return errorCodeClasses.stream().map(errorCodeClass -> {
                                try {
                                    return Arrays.stream(errorCodeClass.getEnumConstants())
                                            .filter(i -> Objects.equals(i.getErrorReason().getCode(), code))
                                            .findAny().orElse(null);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .filter(Objects::nonNull);
                }).toArray(BaseErrorCode[]::new);

                generateErrorCodeResponseExample(operation.getResponses(), errorCodes);
            }

            CustomPageableParameter pageableParameter = handlerMethod.getMethodAnnotation(CustomPageableParameter.class);

            if (pageableParameter != null) {

                Class<?> criteria = pageableParameter.sortCriteria();

                StringBuilder sb = new StringBuilder();
                sb.append("정렬 기준: 속성(,asc|desc) 형식입니다. 기본 정렬 순서는 오름차순이며, 여러 개의 정렬 기준을 지원합니다.<br>");
                sb.append("<details><summary>속성 보기</summary>");
                sb.append("<table style=\"border-collapse: collapse; width: 100%;\">\n");
                sb.append("<thead style=\"background-color: #f2f2f2;\">\n");
                sb.append("<tr><th style=\"padding: 8px; text-align: left;\">필드 이름</th><th style=\"padding: 8px; text-align: left;\">타입</th></tr>\n");
                sb.append("</thead>\n");
                sb.append("<tbody>\n");  // tbody는 그대로 유지

// criteria 클래스 내부 필드들 확인
                Field[] fields = criteria.getDeclaredFields();

// 필드마다 설명을 동적으로 설정
                for (Field field : fields) {
                    String description = getDescribableFieldName(field);

                    if (description != null) {
                        sb.append("<tr>\n")
                                .append("<td style=\"padding: 8px; border: 1px solid #ddd;\">")
                                .append(description)
                                .append("</td>\n")
                                .append("<td style=\"padding: 8px; border: 1px solid #ddd;\">")
                                .append(JsonUtils.jsonTypeFrom(field.getType()))
                                .append("</td>\n")
                                .append("</tr>\n");
                    }
                }

                sb.append("</tbody>\n");
                sb.append("</table>\n");
                sb.append("</details><br><br>");

                for (Parameter parameter : operation.getParameters()) {
                    if("sort".equals(parameter.getName())) {
                        parameter.setDescription(sb.toString());
                    }
                }
            }

            //메서드 파라미터 커스터마이징
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

            for (MethodParameter methodParameter : methodParameters) {

                //CustomDescriptionOverride - 파라미터 DTO 클래스 내 특정 필드의 설명을 재정의
                CustomDescriptionOverride customDescriptionOverride = methodParameter.getParameterAnnotation(CustomDescriptionOverride.class);

                if (customDescriptionOverride != null) {
                    String fieldName = customDescriptionOverride.fieldName();
                    String description = customDescriptionOverride.description();

                    Class<?> dtoClass = methodParameter.getParameterType();
                    Optional<Field> fieldOpt = findFieldByName(dtoClass, fieldName);

                    if (fieldOpt.isPresent()) {
                        Field field = fieldOpt.get();
                        io.swagger.v3.oas.annotations.media.Schema schemaAnnotation = field.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);

                        // `@Schema`에 description 업데이트
                        if (schemaAnnotation != null) {
                            // 스키마 객체에 description을 업데이트
                            for (Parameter param : operation.getParameters()) {
                                if (param.getName().equals(fieldName)) {
                                    param.setDescription(description);
                                }
                            }
                        }
                    }
                }
            }

            return operation;
        };
    }

    private String getDescribableFieldName(Field field) {
        Class<?> fieldType = field.getType();
        if (
                fieldType == String.class ||
                        fieldType == Boolean.class || fieldType == boolean.class ||
                        fieldType == Integer.class || fieldType == int.class ||
                        fieldType == Long.class || fieldType == long.class ||
                        fieldType == Float.class || fieldType == float.class ||
                        fieldType == Double.class || fieldType == double.class ||
                        fieldType == Date.class
        ) {
            return field.getName();
        }
        return null;
    }

    private Optional<Field> findFieldByName(Class<?> dtoClass, String fieldName) {
        try {
            return Optional.of(dtoClass.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    private void generateErrorCodeResponseExample(ApiResponses responses, BaseErrorCode[] errorCodes) {

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