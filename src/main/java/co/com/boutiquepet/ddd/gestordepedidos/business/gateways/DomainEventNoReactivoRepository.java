package co.com.boutiquepet.ddd.gestordepedidos.business.gateways;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;

import java.util.List;

public interface DomainEventNoReactivoRepository {
    List<DomainEvent> findById(String aggregateId);
    DomainEvent saveEvent(DomainEvent event);
}
