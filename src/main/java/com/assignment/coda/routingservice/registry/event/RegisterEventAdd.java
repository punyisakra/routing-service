package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class RegisterEventAdd implements RegistryEventAction {
    @Override
    public void process(Queue<Instance> instanceQueue, RegistryEvent event) {
        if (!event.getAction().equals("add")) return;
        instanceQueue.add(event.getInstance());
    }
}
