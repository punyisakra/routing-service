package com.assignment.coda.routingservice.registry.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class RegistryEventActionFactoryTest {

    @InjectMocks
    private RegistryEventActionFactory registryEventActionFactory;

    @Test
    public void getAction_addAction_returnRegisterEventAdd() {
        RegistryEventAction action = registryEventActionFactory.getAction("add");
        assertThat(action, instanceOf(RegisterEventAdd.class));
    }

    @Test
    public void getAction_removeAction_returnRegisterEventRemove() {
        RegistryEventAction action = registryEventActionFactory.getAction("remove");
        assertThat(action, instanceOf(RegisterEventRemove.class));
    }

    @Test
    public void getAction_unknownAction_throwUnsupportedException() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> registryEventActionFactory.getAction("unk"));
    }
}