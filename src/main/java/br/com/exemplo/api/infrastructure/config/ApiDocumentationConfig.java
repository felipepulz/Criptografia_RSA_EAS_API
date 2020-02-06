package br.com.exemplo.api.infrastructure.config;

import br.com.exemplo.api.ExemploApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApiDocumentationConfig {

    private static final String TITTLE = "Exemplo API";
    private static final String DESCRIPTION = "Exemplos descrição";

    @Bean
    Docket apiDocket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors
                        .basePackage(ExemploApplication.class.getPackageName()))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    ApiInfo apiInfo(@Value("${springfox.documentation.info.version}") String version) {
        return new ApiInfoBuilder().title(TITTLE).description(DESCRIPTION).version(version)
                .build();
    }
}
