package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.EstadoPedido;

import java.util.Set;
import java.util.stream.Collectors;

public class PedidoCancelado extends DomainEvent {
    private EstadoPedido estadoPedido;
    private Set<DetallePedido> detallePedidos;
    public PedidoCancelado(Set<DetallePedido> detallePedidos) {
        super(PedidoCancelado.class.getName());
        this.estadoPedido = new EstadoPedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido.CANCELADO);
        this.detallePedidos = detallePedidos
                .stream()
                .map(detallePedido -> {
                    detallePedido.pasarACanceladoElDetallePorCancelacionDePedido();
                    return detallePedido;
                }).collect(Collectors.toSet());
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
}
