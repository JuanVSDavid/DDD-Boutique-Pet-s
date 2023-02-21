package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

public class TotalPedido implements ValueObject<BigDecimal> {
    private BigDecimal totalPedido;
    public TotalPedido(BigDecimal totalPedido){
        this.totalPedido = Objects.requireNonNull(totalPedido);
    }
    @Override
    public BigDecimal value() {
        return totalPedido;
    }
}
