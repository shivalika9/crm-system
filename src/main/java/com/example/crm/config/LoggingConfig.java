package com.example.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter f = new CommonsRequestLoggingFilter();
        f.setIncludeClientInfo(true);     // IP, session
        f.setIncludeQueryString(true);    // ?q=...
        f.setIncludeHeaders(false);       // set true if you want headers
        f.setIncludePayload(true);        // request body
        f.setMaxPayloadLength(10000);     // payload limit
        f.setAfterMessagePrefix("HTTP REQUEST -> ");
        return f;
    }
}
