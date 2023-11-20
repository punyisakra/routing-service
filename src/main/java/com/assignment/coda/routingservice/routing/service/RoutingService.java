package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RoutingService {

    protected final Logger logger = LoggerFactory.getLogger(RoutingService.class);

    protected RegistryService registryService;

    public RoutingService(RegistryService registryService) {
        this.registryService = registryService;
    }

    public abstract Instance getNextInstance();
}
