package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventNoReactivoRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.UseCaseForCommandNoReactivo;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.CrearPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CrearPedidoNoReactivoUseCase extends UseCaseForCommandNoReactivo<CrearPedidoCommand> {

    private final DomainEventNoReactivoRepository repository;
    public CrearPedidoNoReactivoUseCase(DomainEventNoReactivoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(CrearPedidoCommand command) {
        Set<String> idDeLasTransacciones = new HashSet<>();
        idDeLasTransacciones.add(command.getIdTransaccion());
        Pedido pedido =
                new Pedido(
                        PedidoId.of(command.getPedidoId()),
                        ClienteId.of(command.getClienteId()),
                        new TotalPedido(command.getTotalPedido()),
                        command.
                                getDetallesPedido()
                                .stream()
                                .map(detalle ->
                                        DetallePedido
                                                .builder()
                                                .id(DetallePedidoId.of(detalle.getDetallePedidoId()))
                                                .medidas(new Medidas(detalle.getContornoCuelloCM(), detalle.getContornoPechoCM(), detalle.getLargoLomoCM(), detalle.getLargoMangaCM()))
                                                .productoId(ProductoId.of(detalle.getProductoId()))
                                                .totalDetallePedido(new TotalDetallePedido(detalle.getSubTotal(), detalle.getCantidad()))
                                                .informacionDelDetalleDelPedido(new InformacionDelDetalleDelPedido(detalle.getEsDelStock()))
                                                .estadoDetallePedido(
                                                        detalle.getEsDelStock()
                                                                ? new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_EMPACAR)
                                                                : new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.EN_PROCESO)
                                                        )
                                                .build()
                                )
                                .collect(Collectors.toSet()),
                        new InformacionDelPagoDelPedido(command.getValorCancelado(), idDeLasTransacciones)
                );
        return pedido.getUncommittedChanges()
                .stream()
                .map(repository::saveEvent)
                .collect(Collectors.toList());
    }
}
