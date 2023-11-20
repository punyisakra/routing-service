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

@Service
public class RegistryServiceImpl implements RegistryService {

    private final Logger logger = LoggerFactory.getLogger(RegistryServiceImpl.class);

    private final Queue<Instance> instanceQueue = new ConcurrentLinkedQueue<>();

    private RegistryEventActionFactory registryEventActionFactory;

    public RegistryServiceImpl(RegistryEventActionFactory registryEventActionFactory) {
        this.registryEventActionFactory = registryEventActionFactory;
    }

    @Override
    public void processEvent(RegistryEvent event) {
        RegistryEventAction action = registryEventActionFactory.getAction(event.getAction());
        action.process(instanceQueue, event);
        logger.info("Finished process: {}", instanceQueue);
    }

    @Override
    public Queue<Instance> getRegistryQueue() {
        return instanceQueue;
    }
}
