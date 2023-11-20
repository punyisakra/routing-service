package com.assignment.coda.routingservice.registry.dto;

import com.assignment.coda.routingservice.routing.dto.Instance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class RegistryEvent {
    private String action;
    private Instance instance;
}
