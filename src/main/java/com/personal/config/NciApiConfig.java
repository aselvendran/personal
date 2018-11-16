package com.personal.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
//@PropertySource("classpath:/integration/appnexus.properties")
@ComponentScan({
//    "com.varick.metadsp.controller.appnexus",
//    "com.varick.metadsp.integration.appnexus"
})

public class NciApiConfig {

//    @Value("${apnx.baseUrl}")
    private String baseUrl;

    @Bean
    public RestTemplate apnxRestTemplate(RestTemplateBuilder builder) {
        return builder
            .defaultMessageConverters()
            .rootUri(baseUrl)
            .build();
    }

}
