package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

/**
 * A client interface implemented by {@link SimpleClientImpl}
 * to pass the HTTP request to simple-api-service
 */
public interface SimpleClient {

    /**
     * Pass the request to Simple POST API which accepts json payload and returns the exact copy of it.
     * Will return {@link org.springframework.http.HttpStatus HttpStatus} 400: Bad Request if the input
     * payload is an invalid json
     * @param payload the json payload of type {@link String}
     * @return the exact copy of the input payload, wrapped with {@link org.springframework.http.HttpStatus HttpStatus}
     * @throws RestClientException when there is any error when sending POST HTTP request
     */
    ResponseEntity<String> post(Instance instance, String payload) throws RestClientException;
}
