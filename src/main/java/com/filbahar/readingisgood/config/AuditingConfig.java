package com.filbahar.readingisgood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Configuration
@EnableMongoAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<String> getAuditorAware() {
        return new AuditorAwareImpl();
    }

}
