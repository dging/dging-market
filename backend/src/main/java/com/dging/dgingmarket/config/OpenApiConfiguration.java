package com.dging.dgingmarket.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
}
