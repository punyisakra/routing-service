package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

/**
 * A service interface implemented by {@link SimpleServiceImpl}
 * to handle simple-api-service's call logic
 */
public interface SimpleService {

    /**
     * Perform POST call to simple-api-service
     * If an {@link RestClientException} is thrown, the method with retry with the same argument.
     * If the maxAttempts is reached, the recovery method {@link #postRecover(RestClientException, Instance, String) postRecover} will be executed.
     * @param instance an instance of type {@link Instance} in registry queue which will handle the request
     * @param payload the json payload of type {@link String}
     * @return response string returned from simple-api-service
     * @throws RestClientException when there is any error with the endpoint
     */
    ResponseEntity<String> post(Instance instance, String payload) throws RestClientException;

    /**
     * Recovery method for {@link #post( Instance, String) post}
     * @param e         an exception {@link RestClientException} of the last try
     * @param instance an instance of type {@link Instance} in registry queue which will handle the request
     * @param payload the json payload of type {@link String}
     * @return {@link org.springframework.http.HttpStatus HttpStatus} 500 Internal Server Error
     */
    ResponseEntity<String> postRecover(RestClientException e, Instance instance, String payload);
}
