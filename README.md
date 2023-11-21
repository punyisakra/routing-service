# routing-service
Routing Service as gateway and load balancer. Pass the HTTP Request to corresponsing service that registered with **registry-service** in a round-robin manner. Consisting of 2 modules: routing and registry. </br></br>

### Routing Module
Handle HTTP request for Routing API, passing the request to the corresponding service based on registry queue in round-robin manner

### Registry Module
Handle logic to process registry event subscribed from **registry-service**. Update the local registry queue to be in sync with the registry list in **registry-service**
</br>

## System Flow
### Routing the HTTP Request to simple-api-service

```mermaid
sequenceDiagram
    participant client
    participant routing-service
    participant simple-api-service(1)
    participant simple-api-service(2)
    client->>routing-service: POST: /simples {<payload>}
    routing-service->>routing-service: get next instance
    routing-service->>simple-api-service(1): POST: /simples {<payload>}
    simple-api-service(1)->>routing-service: HTTP Response 200: OK {<payload>}
    routing-service->>client: HTTP Response 200: OK {<payload>}
    client->>routing-service: POST: /simples {<payload>}
    routing-service->>routing-service: get next instance
    loop retry
        routing-service->>simple-api-service(2): POST: /simples {<payload>}
        simple-api-service(2)->>routing-service: RestClientException
    end
    routing-service->>client: HTTP Response 500: Internal Server Error
```

### Process registry event: Add Event

```mermaid
sequenceDiagram
    participant simple-api-service
    participant registry-service
    participant routing-service
    simple-api-service->>simple-api-service: handle application event when initialized
    simple-api-service->>registry-service: POST: /registries {service name, port}
    registry-service->>registry-service: add service to registry list
    registry-service->>routing-service: publish "add" event
    routing-service->>routing-service: update queue by RegistryEventAdd.process()
```

### Process registry event: Remove Event

```mermaid
sequenceDiagram
    participant simple-api-service
    participant registry-service
    participant routing-service
    loop retry
        registry-service->>simple-api-service: GET: /actuator/health
        simple-api-service->>registry-service: HTTP Reponse 200: OK {"DOWN"}
    end
    registry-service->>registry-service: remove service from registry list
    registry-service->>routing-service: publish "remove" event
    routing-service->>routing-service: update queue by RegistryEventRemove.process()
```

For more information on other flows and services, see also [simple-api-service](https://github.com/punyisakra/simple-api-service) and [registry-service](https://github.com/punyisakra/registry-service)
