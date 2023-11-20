package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryListener {

    private final Logger logger = LoggerFactory.getLogger(RegistryListener.class);

    private RegistryService registryService;

    @Autowired
    public RegistryListener(RegistryService registryService) {
        this.registryService = registryService;
    }

    @RabbitListener(queues = {"${rabbitmq.routing.key}"})
    public void processRegistryEvent(final RegistryEvent event) {
        logger.info("Process message from queue, event: {}", event);
        registryService.processEvent(event);
    }

}
