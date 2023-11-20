package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.routing.dto.Instance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SimpleClientTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private SimpleClientImpl simpleClient;

    @Test
    public void post_success_returnSuccessResponse() {
        Instance instance = new Instance("name", "1234");
        String payload = "payload";
        String url = "http://localhost:1234/simples";

        when(restTemplateMock.postForEntity(url, payload, String.class))
                .thenReturn(ResponseEntity.ok("ok"));
        ResponseEntity<String> response = simpleClient.post(instance, payload);

        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        verify(restTemplateMock, times(1)).postForEntity(url, payload, String.class);
    }

    @Test
    public void post_failed_forwardThrownException() {
        Instance instance = new Instance("name", "1234");
        String payload = "payload";
        String url = "http://localhost:1234/simples";

        when(restTemplateMock.postForEntity(url, payload, String.class))
                .thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> simpleClient.post(instance, payload));
        verify(restTemplateMock, times(1)).postForEntity(url, payload, String.class);
    }
}