package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class SimpleServiceImpl implements SimpleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);

    private SimpleClient simpleClient;

    @Autowired
    public SimpleServiceImpl(SimpleClient simpleClient) {
        this.simpleClient = simpleClient;
    }

    @Override
    @Retryable(retryFor = RestClientException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public ResponseEntity<String> post(Instance instance, String payload) throws RestClientException {
        return simpleClient.post(instance, payload);
    }

    @Override
    @Recover
    public ResponseEntity<String> postRecover(RestClientException e, Instance instance, String payload) {
        logger.error("Reach max retry for simple api, instance: {}, payload: {}", instance, payload);
        return ResponseEntity.internalServerError().body("unexpected error");
    }
}
