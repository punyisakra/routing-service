package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public interface SimpleService {
    ResponseEntity<String> post(Instance instance, String payload) throws RestClientException;

    ResponseEntity<String> postRecover(RestClientException e, Instance instance, String payload);
}
