package org.khanhdunk.web_dat_ve_xem_phim.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    // Cấu hình thông tin chung cho Swagger UI
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Web đặt vé xem phim API")
                        .version("1.0")
                        .description("Tài liệu API cho hệ thống đặt vé xem phim"));
    }

    // Giới hạn package cần quét nếu cần
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("web-dat-ve-xem-phim")
                .packagesToScan("org.khanhdunk.web_dat_ve_xem_phim.Controller")
                .build();
    }
}
