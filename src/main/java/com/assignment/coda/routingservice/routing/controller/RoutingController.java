package com.assignment.coda.routingservice.routing.controller;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.routing.service.RoutingService;
import com.assignment.coda.routingservice.routing.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RoutingController {

    private final Logger logger = LoggerFactory.getLogger(RoutingController.class);

    private RoutingService routingService;

    private SimpleService simpleService;

    public RoutingController(RoutingService routingService, SimpleService simpleService) {
        this.routingService = routingService;
        this.simpleService = simpleService;
    }

    @RequestMapping(
            value = "/routes",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> route(@RequestBody String payload) {
        logger.info("{} /routes, REQUEST PAYLOAD: {}", RequestMethod.POST, payload);
        Instance nextInstance = routingService.getNextInstance();
        ResponseEntity<String> response = Objects.isNull(nextInstance)
                ? ResponseEntity.noContent().build()
                : simpleService.post(nextInstance, payload);
        logger.info("RESPONSE: {}", response.getStatusCode());
        return response;
    }
}
