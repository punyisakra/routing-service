package com.assignment.coda.routingservice.registry.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import com.assignment.coda.routingservice.registry.event.RegistryEventAction;
import com.assignment.coda.routingservice.registry.event.RegistryEventActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A service class providing an implementation to {@link RegistryService}
 * to handle registry event logic
 */
@Service
public class RegistryServiceImpl implements RegistryService {

    private final Logger logger = LoggerFactory.getLogger(RegistryServiceImpl.class);

    private final Queue<Instance> instanceQueue = new ConcurrentLinkedQueue<>();

    private RegistryEventActionFactory registryEventActionFactory;

    public RegistryServiceImpl(RegistryEventActionFactory registryEventActionFactory) {
        this.registryEventActionFactory = registryEventActionFactory;
    }

    /**
     * Process registry event based on the event's action. Depend on
     * {@link RegistryEventActionFactory} to create corresponding {@link RegistryEventAction}
     * to perform an action on local registry queue.
     * @param event a {@link RegistryEvent} represent a change made on the registry list
     *              in registry-service
     */
    @Override
    public void processEvent(RegistryEvent event) {
        RegistryEventAction action = registryEventActionFactory.getAction(event.getAction());
        action.process(instanceQueue, event);
        logger.info("Finished process: {}", instanceQueue);
    }

    /**
     * Retrieve local registry queue, which reflect the registry list in registry-service
     * @return a queue of type {@link Queue<Instance>} that represented all {@link Instance}s which were
     * registered with the registry-service
     */
    @Override
    public Queue<Instance> getRegistryQueue() {
        return instanceQueue;
    }
}
