package org.example.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Swagger文档")
                .description("接口文档").termsOfServiceUrl("www.baidu.com")
                .contact(new Contact("Localhost", "www.baidu.com", "e@mail.com"))
                .version("0.1")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("内测")  // 分组名称
                .select()
                // 指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.example.provider.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
