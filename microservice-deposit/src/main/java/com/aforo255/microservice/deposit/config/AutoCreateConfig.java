package com.aforo255.microservice.deposit.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {

    /**
     * Usar un tópico existente sino crea uno nuevo.
     */
    public NewTopic despositEvent() {
        return TopicBuilder.name("transaction-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
