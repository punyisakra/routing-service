package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A service abstract class providing based to {@link RoundRobinRoutingServiceImpl}
 * to provide logic for registry queue
 */
public abstract class RoutingService {

    protected final Logger logger = LoggerFactory.getLogger(RoutingService.class);

    protected RegistryService registryService;

    public RoutingService(RegistryService registryService) {
        this.registryService = registryService;
    }

    /**
     * Get a next instance to pass the Http request to
     * @return a next {@link Instance} to pass the request to, return null if
     * the registry queue is empty
     */
    public abstract Instance getNextInstance();
}
