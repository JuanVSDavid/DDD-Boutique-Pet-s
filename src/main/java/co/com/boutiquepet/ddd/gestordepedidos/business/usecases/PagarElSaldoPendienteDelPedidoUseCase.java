package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.PagarElSaldoPendienteDelPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.PedidoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PagarElSaldoPendienteDelPedidoUseCase extends UseCaseForCommand<PagarElSaldoPendienteDelPedidoCommand> {
    private final DomainEventRepository repository;
    public PagarElSaldoPendienteDelPedidoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<PagarElSaldoPendienteDelPedidoCommand> pagadoElSaldoPendienteDelPedidoCommandMono) {
        return pagadoElSaldoPendienteDelPedidoCommandMono
                .flatMapMany(command -> repository.findById(command.getPedidoId())
                        .collectList()
                        .flatMapIterable(events -> {
                            Pedido pedido = Pedido.from(PedidoId.of(command.getPedidoId()), events);
                            pedido.pagarElValorDelSaldoPendiente(command.getValorCancelado(), command.getIdTransaccion());
                            return pedido.getUncommittedChanges();
                        })
                        .flatMap(event -> repository.saveEvent(event))
                );
    }
}
