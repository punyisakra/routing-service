package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class RegisterEventRemove implements RegistryEventAction {
    @Override
    public void process(Queue<Instance> instanceQueue, RegistryEvent event) {
        if (!event.getAction().equals("remove") || !instanceQueue.contains(event.getInstance()))
            return;
        instanceQueue.remove(event.getInstance());
    }
}
