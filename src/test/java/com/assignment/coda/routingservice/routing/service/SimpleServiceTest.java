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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SimpleServiceTest {

    @Mock
    private SimpleClient simpleClientMock;

    @InjectMocks
    private SimpleServiceImpl simpleService;

    @Test
    public void post_passInstanceAndPayload() {
        Instance instance = new Instance("name", "1234");
        String payload = "payload";

        when(simpleClientMock.post(instance, payload)).thenReturn(ResponseEntity.ok("ok"));
        ResponseEntity<String> response = simpleService.post(instance, payload);

        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        verify(simpleClientMock, times(1)).post(instance, payload);
    }

    @Test
    public void postRecover_passInstanceAndPayload() {
        Instance instance = new Instance("name", "1234");
        String payload = "payload";
        RestClientException e = new RestClientException("err");

        ResponseEntity<String> response = simpleService.postRecover(e, instance, payload);

        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}