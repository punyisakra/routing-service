package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;

import java.util.Queue;

/**
 * A component interface implemented by {@link RegistryEventAdd} and
 * {@link  RegistryEventRemove} to perform operation on local registry queue
 */
public interface RegistryEventAction {

    /**
     * Process the event by performing an operation on the registry queue
     * @param instanceQueue the registry queue {@link Queue<Instance>} storing all
     *                      instances that registered in registry-service
     * @param event         a {@link RegistryEvent} represent a change made on the registry list
     *                      in registry-service
     */
    void process(Queue<Instance> instanceQueue, RegistryEvent event);
}
