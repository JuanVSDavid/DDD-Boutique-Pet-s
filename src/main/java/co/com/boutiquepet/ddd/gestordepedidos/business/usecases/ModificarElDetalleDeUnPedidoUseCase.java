package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.CrearPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.ModificarElDetalleDeUnPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ModificarElDetalleDeUnPedidoUseCase extends UseCaseForCommand<ModificarElDetalleDeUnPedidoCommand> {
    private final DomainEventRepository repository;
    public ModificarElDetalleDeUnPedidoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<ModificarElDetalleDeUnPedidoCommand> modificarElDetalleDeUnPedidoCommandMono) {
        return modificarElDetalleDeUnPedidoCommandMono
                .flatMapMany(command -> repository.findById(command.getPedidoId())
                        .collectList()
                        .flatMapIterable(events -> {
                            Pedido pedido = Pedido.from(PedidoId.of(command.getPedidoId()), events);
                            pedido.modificarElDetalle(
                                    DetallePedidoId.of(command.getDetallePedidoId()),
                                    new Medidas(
                                            command.getContornoCuelloCM(),
                                            command.getContornoPechoCM(),
                                            command.getLargoLomoCM(),
                                            command.getLargoMangaCM()
                                    ),
                                    ProductoId.of(command.getProductoId()),
                                    new TotalDetallePedido(command.getSubTotal(), command.getCantidad()),
                                    new InformacionDelDetalleDelPedido(command.getEsDelStock())
                            );
                            return pedido.getUncommittedChanges();
                        }).flatMap(repository::saveEvent)
                );
    }
}
