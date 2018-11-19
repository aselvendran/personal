package com.personal.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:/integration/nci.properties")
@ComponentScan({
        "com.personal.controller.nci",
        "com.personal.integration.nci"
})

public class NciApiConfig {

    @Value("${nci.baseUrl}")
    private String baseUrl;

    @Bean
    public RestTemplate nciRestTemplate(RestTemplateBuilder builder) {
        return builder
                .defaultMessageConverters()
                .rootUri(baseUrl)
                .build();
    }

}
