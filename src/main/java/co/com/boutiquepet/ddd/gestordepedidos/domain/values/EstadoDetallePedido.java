package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class EstadoDetallePedido implements ValueObject<co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido> {
    private co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido estadoDetallePedido;
    public EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido estadoDetallePedido){
        this.estadoDetallePedido = Objects.requireNonNull(estadoDetallePedido);
    }
    @Override
    public co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido value() {
        return estadoDetallePedido;
    }
}
