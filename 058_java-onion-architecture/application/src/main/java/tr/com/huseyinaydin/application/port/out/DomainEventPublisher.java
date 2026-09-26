package tr.com.huseyinaydin.application.port.out;

import tr.com.huseyinaydin.domain.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
