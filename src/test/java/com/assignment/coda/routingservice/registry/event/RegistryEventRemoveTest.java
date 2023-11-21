package com.assignment.coda.routingservice.registry.event;

import com.assignment.coda.routingservice.registry.dto.RegistryEvent;
import com.assignment.coda.routingservice.routing.dto.Instance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
class RegistryEventRemoveTest {

    @InjectMocks
    private RegistryEventRemove registryEventRemove;

    @Test
    public void process_actionMatches_addInstanceToQueue() {
        Instance instance = new Instance("name", "1234");
        RegistryEvent event = new RegistryEvent("remove", instance);
        Queue<Instance> queue = new LinkedList<>();
        queue.add(instance);

        registryEventRemove.process(queue, event);

        assertThat(queue.size(), is(0));
    }

    @Test
    public void process_actionNotMatch_doNotAddInstanceToQueue() {
        Instance instance = new Instance("name", "1234");
        RegistryEvent event = new RegistryEvent("add", instance);
        Queue<Instance> queue = new LinkedList<>();
        queue.add(instance);

        registryEventRemove.process(queue, event);

        assertThat(queue.size(), is(1));
        assertThat(queue.peek(), is(instance));
    }
}