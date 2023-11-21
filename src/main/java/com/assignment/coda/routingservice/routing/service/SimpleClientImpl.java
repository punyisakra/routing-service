package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * A client class providing an implementation to {@link SimpleClient}
 * to pass the HTTP request to simple-api-service
 */
@Service
public class SimpleClientImpl implements SimpleClient {

    private final Logger logger = LoggerFactory.getLogger(SimpleClientImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public SimpleClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Pass the request to Simple POST API which accepts json payload and returns the exact copy of it.
     * Will return {@link org.springframework.http.HttpStatus HttpStatus} 400: Bad Request if the input
     * payload is an invalid json
     * @param payload the json payload of type {@link String}
     * @return the exact copy of the input payload, wrapped with {@link org.springframework.http.HttpStatus HttpStatus}
     * @throws RestClientException when there is any error when sending POST HTTP request
     */
    @Override
    public ResponseEntity<String> post(Instance instance, String payload) throws RestClientException {
        String url = String.format("http://localhost:%s/simples", instance.getPort());
        logger.info("{} {}", RequestMethod.POST, url);
        return restTemplate.postForEntity(url, payload, String.class);
    }
}
