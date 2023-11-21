package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * A service class providing an extension to {@link RoutingService}
 * to provide round-robin logic of registry queue
 */
@Service
public class RoundRobinRoutingServiceImpl extends RoutingService {

    @Autowired
    public RoundRobinRoutingServiceImpl(RegistryService registryService) {
        super(registryService);
    }

    /**
     * Get a next instance to pass the Http request to in a round-robin manner
     * @return a next {@link Instance} to pass the request to, return null if
     * the registry queue is empty
     */
    @Override
    @Synchronized
    public Instance getNextInstance() {
        logger.debug("Round robin routing");
        Queue<Instance> instanceQueue = registryService.getRegistryQueue();
        if (instanceQueue.isEmpty()) return null;
        logger.debug("Instance queue before: {}", instanceQueue);
        Instance next = instanceQueue.poll();
        instanceQueue.add(next);
        logger.debug("Instance queue after: {}", instanceQueue);
        return next;
    }
}
