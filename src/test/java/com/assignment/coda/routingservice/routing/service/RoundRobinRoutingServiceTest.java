package com.assignment.coda.routingservice.routing.service;

import com.assignment.coda.routingservice.registry.service.RegistryService;
import com.assignment.coda.routingservice.routing.dto.Instance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RoundRobinRoutingServiceTest {

    @Mock
    private RegistryService registryServiceMock;

    @InjectMocks
    private RoundRobinRoutingServiceImpl routingService;

    @Test
    public void getNextInstance_getInstanceBasedOnQueueOrder() {
        // given: There are 3 instances in the queue
        Instance instance1 = new Instance("instance1", "1111");
        Instance instance2 = new Instance("instance2", "2222");
        Instance instance3 = new Instance("instance3", "3333");
        Queue<Instance> instanceQueue = new LinkedList<>();
        instanceQueue.add(instance1);
        instanceQueue.add(instance2);
        instanceQueue.add(instance3);

        // when: Get the next instance from queue
        when(registryServiceMock.getRegistryQueue()).thenReturn(instanceQueue);
        Instance next = routingService.getNextInstance();

        // then: Assert that the next instance is instance1
        assertThat(next, is(instance1));
        verify(registryServiceMock, times(1)).getRegistryQueue();

        // when: Get the next instance from queue
        next = routingService.getNextInstance();

        // then: Assert that the next instance is instance2
        assertThat(next, is(instance2));

        // when: Get the next instance from queue
        next = routingService.getNextInstance();

        // then: Assert that the next instance is instance3
        assertThat(next, is(instance3));

        // when: Get the next instance from queue
        next = routingService.getNextInstance();

        // then: Assert that the next instance is instance1
        assertThat(next, is(instance1));
    }
}