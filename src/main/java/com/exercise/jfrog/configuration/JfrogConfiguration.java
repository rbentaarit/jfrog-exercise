package com.exercise.jfrog.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JfrogConfiguration {

    @Value("${jfrog.login}")
    String login;
    @Value("${jfrog.password}")
    String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(login, password);
    }
}
