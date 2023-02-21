package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.CancelarPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.CrearPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.PedidoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CancelarPedidoUseCase extends UseCaseForCommand<CancelarPedidoCommand> {
    private final DomainEventRepository repository;
    public CancelarPedidoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<CancelarPedidoCommand> cancelarPedidoCommandMono) {
        return cancelarPedidoCommandMono
                .flatMapMany(command -> repository.findById(command.getPedidoId())
                        .collectList()
                        .flatMapIterable(events -> {
                            Pedido pedido = Pedido.from(PedidoId.of(command.getPedidoId()), events);
                            pedido.cancelarPedido();
                            return pedido.getUncommittedChanges();
                        }).flatMap(repository::saveEvent)
                );
    }
}
