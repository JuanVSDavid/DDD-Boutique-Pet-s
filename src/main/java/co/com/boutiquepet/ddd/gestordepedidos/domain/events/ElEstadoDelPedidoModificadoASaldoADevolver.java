package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.EstadoPedido;

public class ElEstadoDelPedidoModificadoASaldoADevolver extends DomainEvent {
    private EstadoPedido estadoPedido;
    public ElEstadoDelPedidoModificadoASaldoADevolver() {
        super(ElEstadoDelPedidoModificadoASaldoADevolver.class.getName());
        this.estadoPedido = new EstadoPedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido.PENDIENTE_SALDO);

    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }
}
