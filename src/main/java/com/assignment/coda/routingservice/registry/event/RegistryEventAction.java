package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;

import java.util.Queue;

public interface RegistryEventAction {
    void process(Queue<Instance> instanceQueue, RegistryEvent event);
}
