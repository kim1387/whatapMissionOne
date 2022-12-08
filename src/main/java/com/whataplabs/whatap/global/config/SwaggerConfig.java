package com.whataplabs.whatap.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI whaTapOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("WhaTap 1차 과제 API")
                .description("WhaTap 1차 과제 API 입니다.")
                .version("v0.0.1"));
  }
}
