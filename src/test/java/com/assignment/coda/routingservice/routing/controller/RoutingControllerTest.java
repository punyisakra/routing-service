package com.assignment.coda.routingservice.routing.controller;

import com.assignment.coda.routingservice.routing.dto.Instance;
import com.assignment.coda.routingservice.routing.service.RoutingService;
import com.assignment.coda.routingservice.routing.service.SimpleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RoutingControllerTest {

    @Mock
    private RoutingService routingServiceMock;

    @Mock
    private SimpleService simpleServiceMock;

    @InjectMocks
    private RoutingController routingController;

    @Test
    public void route_hasInstance_postRequestToTheInstance() {
        String payload = "{\"test\":1}";
        Instance instance = new Instance("name", "1234");

        when(routingServiceMock.getNextInstance()).thenReturn(instance);
        when(simpleServiceMock.post(instance, payload)).thenReturn(ResponseEntity.ok("ok"));
        ResponseEntity<String> response = routingController.route(payload);

        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        verify(routingServiceMock, times(1)).getNextInstance();
        verify(simpleServiceMock, times(1)).post(instance, payload);
    }

    @Test
    public void route_hasNoInstance_donNotPostRequest() {
        String payload = "{\"test\":1}";

        when(routingServiceMock.getNextInstance()).thenReturn(null);
        ResponseEntity<String> response = routingController.route(payload);

        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        verify(routingServiceMock, times(1)).getNextInstance();
        verify(simpleServiceMock, times(0)).post(any(), eq(payload));
    }
}