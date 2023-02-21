package co.com.boutiquepet.ddd.gestordepedidos.business.gateways;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
}
