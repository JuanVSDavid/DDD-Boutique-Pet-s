package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import java.util.Objects;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

public class EstadoPedido implements ValueObject<co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido> {
    private co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido estadoPedido;
    public EstadoPedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido estadoPedido){
        this.estadoPedido = Objects.requireNonNull(estadoPedido);
    }
    @Override
    public co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido value() {
        return estadoPedido;
    }
}
