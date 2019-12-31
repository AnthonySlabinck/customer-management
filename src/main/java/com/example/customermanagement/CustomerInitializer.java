package com.example.customermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Stream;

@Component
class CustomerInitializer {

    private final CustomerRepository repository;

    private final ObjectMapper mapper;

    @Value("classpath:/data.json")
    private Resource data;

    CustomerInitializer(CustomerRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @EventListener
    void on(ApplicationReadyEvent event) throws IOException {
        if (repository.count() > 0) {
            return;
        }

        Stream.of(mapper.readValue(data.getFile(), Customer[].class))
                .forEach(repository::save);
    }
}
