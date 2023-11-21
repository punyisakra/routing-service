package com.assignment.coda.routingservice.registry.dto;

import com.assignment.coda.routingservice.routing.dto.Instance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Represent an event when the registry list in the registry-service is changed,
 * used for receiving the change from registry-service via RabbitMQ
 * */
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class RegistryEvent {
    /**
     * Action string such as "add" or "remove"
     */
    private String action;

    /**
     * {@link Instance} that was changed depending on the action property
     */
    private Instance instance;
}
