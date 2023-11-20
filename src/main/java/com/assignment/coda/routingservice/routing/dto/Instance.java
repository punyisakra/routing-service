package com.assignment.coda.routingservice.routing.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Instance {
    private String name;
    private String port;

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        Instance oInstance = (Instance) obj;
        return this.name.equals(oInstance.getName())
                && this.port.equals(oInstance.getPort());
    }
}
