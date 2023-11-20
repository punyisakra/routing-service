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

@Service
public class SimpleClientImpl implements SimpleClient {

    private final Logger logger = LoggerFactory.getLogger(SimpleClientImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public SimpleClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> post(Instance instance, String payload) throws RestClientException {
        String url = String.format("http://localhost:%s/simples", instance.getPort());
        logger.info("{} {}", RequestMethod.POST, url);
        return restTemplate.postForEntity(url, payload, String.class);
    }
}
