package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TotalDetallePedido implements ValueObject<TotalDetallePedido.Props> {
    private BigDecimal subTotal;
    private BigInteger cantidad;
    public TotalDetallePedido(BigDecimal subTotal, BigInteger cantidad){
        if(!(cantidad.compareTo(new BigInteger("0")) > 0)){
            throw new IllegalArgumentException("La cantidad no puede ser cero.");
        }
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public BigDecimal subTotal() {
                return subTotal;
            }

            @Override
            public BigInteger cantidad() {
                return cantidad;
            }

            @Override
            public BigDecimal total() {
                return subTotal.multiply(new BigDecimal(cantidad));
            }
        };
    }


    public interface Props{
        BigDecimal subTotal();
        BigInteger cantidad();
        BigDecimal total();
    }
}
