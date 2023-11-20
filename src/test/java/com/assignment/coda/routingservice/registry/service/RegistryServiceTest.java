package com.assignment.coda.routingservice.registry.service;

import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import com.assignment.coda.routingservice.registry.event.RegistryEventAction;
import com.assignment.coda.routingservice.registry.event.RegistryEventActionFactory;
import com.assignment.coda.routingservice.routing.dto.Instance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RegistryServiceTest {

    @Mock
    private RegistryEventActionFactory registryEventActionFactoryMock;

    @Mock
    private RegistryEventAction registryEventActionMock;

    @InjectMocks
    private RegistryServiceImpl registryService;

    @Test
    public void processEvent_getActionFromFactoryAndProcess() {
        RegistryEvent event = new RegistryEvent(
                "action",
                new Instance("name", "port"));

        when(registryEventActionFactoryMock.getAction("action")).thenReturn(registryEventActionMock);
        registryService.processEvent(event);

        verify(registryEventActionFactoryMock, times(1)).getAction("action");
        verify(registryEventActionMock, times(1)).process(any(), eq(event));
    }
}