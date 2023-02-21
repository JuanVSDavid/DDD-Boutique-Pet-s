package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class InformacionDelDetalleDelPedido implements ValueObject<InformacionDelDetalleDelPedido.Props> {
    private Boolean esDelStock;

    public InformacionDelDetalleDelPedido(Boolean esDelStock) {
        this.esDelStock = Objects.requireNonNull(esDelStock);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Boolean esDelStock() {
                return esDelStock;
            }
        };
    }

    public interface Props{
        Boolean esDelStock();
    }
}
