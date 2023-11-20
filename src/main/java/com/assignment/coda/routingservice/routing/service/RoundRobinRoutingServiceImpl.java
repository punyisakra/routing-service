package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class RoundRobinRoutingServiceImpl extends RoutingService {

    @Autowired
    public RoundRobinRoutingServiceImpl(RegistryService registryService) {
        super(registryService);
    }

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
