package com.assignment.coda.routingservice.registry.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;

import java.util.Queue;

public interface RegistryService {

    void processEvent(RegistryEvent event);

    Queue<Instance> getRegistryQueue();
}
