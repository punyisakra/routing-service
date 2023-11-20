package com.assignment.coda.routingservice.registry.event;

import org.springframework.stereotype.Service;

@Service
public class RegistryEventActionFactory {

    public RegistryEventAction getAction(String action) {
        return switch (action) {
            case "add" -> new RegisterEventAdd();
            case "remove" -> new RegisterEventRemove();
            default -> throw new UnsupportedOperationException("Unsupported action type");
        };
    }
}
