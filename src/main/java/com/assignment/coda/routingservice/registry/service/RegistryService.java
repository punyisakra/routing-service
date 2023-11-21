package com.assignment.coda.routingservice.registry.service;

import com.assignment.coda.routingservice.registry.event.RegistryEventAction;
import com.assignment.coda.routingservice.registry.event.RegistryEventActionFactory;
import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;

import java.util.Queue;

/**
 * A service interface implemented by {@link RegistryServiceImpl}
 * to handle registry event logic
 */
public interface RegistryService {

    /**
     * Process registry event based on the event's action. Depend on
     * {@link RegistryEventActionFactory} to create corresponding {@link RegistryEventAction}
     * to perform an action on local registry queue.
     * @param event a {@link RegistryEvent} represent a change made on the registry list
     *              in registry-service
     */
    void processEvent(RegistryEvent event);

    /**
     * Retrieve local registry queue, which reflect the registry list in registry-service
     * @return a queue of type {@link Queue<Instance>} that represented all {@link Instance}s which were
     * registered with the registry-service
     */
    Queue<Instance> getRegistryQueue();
}
