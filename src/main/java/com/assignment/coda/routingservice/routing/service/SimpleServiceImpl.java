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

/**
 * A service class providing an implementation to {@link SimpleService}
 * to handle simple-api-service's call logic
 */
@Service
public class SimpleServiceImpl implements SimpleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);

    private SimpleClient simpleClient;

    @Autowired
    public SimpleServiceImpl(SimpleClient simpleClient) {
        this.simpleClient = simpleClient;
    }

    /**
     * Perform POST call to simple-api-service
     * If an {@link RestClientException} is thrown, the method with retry with the same argument.
     * If the maxAttempts is reached, the recovery method {@link #postRecover(RestClientException, Instance, String) postRecover} will be executed.
     * @param instance an instance of type {@link Instance} in registry queue which will handle the request
     * @param payload the json payload of type {@link String}
     * @return response string returned from simple-api-service
     * @throws RestClientException when there is any error with the endpoint
     */
    @Override
    @Retryable(retryFor = RestClientException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public ResponseEntity<String> post(Instance instance, String payload) throws RestClientException {
        return simpleClient.post(instance, payload);
    }

    /**
     * Recovery method for {@link #post( Instance, String) post}
     * @param e         an exception {@link RestClientException} of the last try
     * @param instance an instance of type {@link Instance} in registry queue which will handle the request
     * @param payload the json payload of type {@link String}
     * @return {@link org.springframework.http.HttpStatus HttpStatus} 500 Internal Server Error
     */
    @Override
    @Recover
    public ResponseEntity<String> postRecover(RestClientException e, Instance instance, String payload) {
        logger.error("Reach max retry for simple api, instance: {}, payload: {}", instance, payload);
        return ResponseEntity.internalServerError().body("unexpected error");
    }
}
