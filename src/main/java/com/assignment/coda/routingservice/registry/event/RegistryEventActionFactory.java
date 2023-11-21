package com.assignment.coda.routingservice.registry.event;

import org.springframework.stereotype.Service;

/**
 * Factory class for creating {@link RegistryEventAction}
 */
@Service
public class RegistryEventActionFactory {

    /**
     * Create new class, an implementation of {@link RegistryEventAction} based on the
     * input action.
     * <ul>
     *     <li>If action is "add", then create {@link RegistryEventAdd}</li>
     *     <li>If action is "remove", then create {@link RegistryEventRemove}</li>
     *     <li>Else, throws {@link UnsupportedOperationException}</li>
     * </ul>
     * @param action an action {@link String} of {@link com.assignment.coda.routingservice.registry.dto.RegistryEvent RegistryEvent}
     * @return the class which implemented {@link RegistryEventAction} based on the input action
     * @throws UnsupportedOperationException if the input action string is unsupported
     */
    public RegistryEventAction getAction(String action) {
        return switch (action) {
            case "add" -> new RegistryEventAdd();
            case "remove" -> new RegistryEventRemove();
            default -> throw new UnsupportedOperationException("Unsupported action type");
        };
    }
}
