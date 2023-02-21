package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventNoReactivoRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForCommandNoReactivo;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.ModificarElDetalleDeUnPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class ModificarElDetalleDeUnPedidoNoReactivoUseCase extends UseCaseForCommandNoReactivo<ModificarElDetalleDeUnPedidoCommand> {
    private final DomainEventNoReactivoRepository repository;
    public ModificarElDetalleDeUnPedidoNoReactivoUseCase(DomainEventNoReactivoRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(ModificarElDetalleDeUnPedidoCommand command) {
        var events = repository.findById(command.getPedidoId());
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
        return pedido.getUncommittedChanges()
                .stream()
                .map(repository::saveEvent)
                .collect(Collectors.toList());
    }
}
