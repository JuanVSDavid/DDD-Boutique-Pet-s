package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Proceso;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Tarea;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.PedidoCreado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

public class CrearProcesoPorCreacionDePedidoUseCase extends UseCaseForEvent<PedidoCreado> {
    private final DomainEventRepository repository;
    public CrearProcesoPorCreacionDePedidoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<PedidoCreado> pedidoCreadoMono) {
        return pedidoCreadoMono.flatMapIterable(event -> {
            Proceso proceso =
                    Proceso
                            .builder()
                            .pedidoId(event.getPedidoId())
                            .administradorId(null)
                            .tareas(Proceso.crearTareas(event.getDetallePedidos()))
                            .build();
            return proceso.getUncommittedChanges();
        }).flatMap(repository::saveEvent);
    }
}
