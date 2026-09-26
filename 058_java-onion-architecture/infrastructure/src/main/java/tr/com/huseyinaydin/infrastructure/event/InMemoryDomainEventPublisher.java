package tr.com.huseyinaydin.infrastructure.event;

import tr.com.huseyinaydin.application.port.out.DomainEventPublisher;
import tr.com.huseyinaydin.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InMemoryDomainEventPublisher implements DomainEventPublisher {
    private final List<Consumer<DomainEvent>> listeners = new ArrayList<>();

    public void subscribe(Consumer<DomainEvent> listener) {
        listeners.add(listener);
    }

    @Override
    public void publish(DomainEvent event) {
        listeners.forEach(listener -> listener.accept(event));
    }
}
