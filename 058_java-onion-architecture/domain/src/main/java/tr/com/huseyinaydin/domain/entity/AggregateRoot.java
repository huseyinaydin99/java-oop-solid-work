package tr.com.huseyinaydin.domain.entity;

import tr.com.huseyinaydin.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> clearDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(events);
    }
}
