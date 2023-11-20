package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import com.assignment.coda.routingservice.registry.service.RegistryService;
import com.assignment.coda.routingservice.routing.dto.Instance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RegistryListenerTest {

    @Mock
    private RegistryService registryServiceMock;

    @InjectMocks
    private RegistryListener registryListener;

    @Test
    public void processRegistryEvent_callServiceToProcessEvent() {
        RegistryEvent event = new RegistryEvent(
                "action",
                new Instance("name", "1245"));

        registryListener.processRegistryEvent(event);

        verify(registryServiceMock, times(1)).processEvent(event);
    }
}