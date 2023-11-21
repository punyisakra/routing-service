package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import org.springframework.stereotype.Component;

import java.util.Queue;

/**
 * A component class implementing {@link RegistryEventAction} to perform
 * REMOVE operation on local registry queue
 */
@Component
public class RegistryEventRemove implements RegistryEventAction {

    /**
     * Process the event by performing an REMOVE operation on the registry queue
     * a.k.a. remove an existing {@link Instance} from the registry queue.
     * @param instanceQueue the registry queue {@link Queue<Instance>} storing all
     *                      instances that registered in registry-service
     * @param event         a {@link RegistryEvent} represent a change made on the registry list
     *                      in registry-service
     */
    @Override
    public void process(Queue<Instance> instanceQueue, RegistryEvent event) {
        if (!event.getAction().equals("remove") || !instanceQueue.contains(event.getInstance()))
            return;
        instanceQueue.remove(event.getInstance());
    }
}
