package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;

import java.math.BigDecimal;

public class ElTotalDelPedidoModificado extends DomainEvent {
    private BigDecimal valorCalculado;
    public ElTotalDelPedidoModificado(BigDecimal valorCalculado) {
        super(ElTotalDelPedidoModificado.class.getName());
        this.valorCalculado = valorCalculado;
    }

    public BigDecimal getValorCalculado() {
        return valorCalculado;
    }
}
