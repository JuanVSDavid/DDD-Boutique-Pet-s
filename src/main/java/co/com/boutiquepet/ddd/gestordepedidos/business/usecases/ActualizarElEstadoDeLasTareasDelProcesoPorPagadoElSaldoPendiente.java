package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.PagadoElSaldoPendienteDelPedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActualizarElEstadoDeLasTareasDelProcesoPorPagadoElSaldoPendiente extends UseCaseForEvent<PagadoElSaldoPendienteDelPedido> {
    private final DomainEventRepository repository;

    public ActualizarElEstadoDeLasTareasDelProcesoPorPagadoElSaldoPendiente(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<PagadoElSaldoPendienteDelPedido> pagadoElSaldoPendienteDelPedidoMono) {
        return null;
    }
}
