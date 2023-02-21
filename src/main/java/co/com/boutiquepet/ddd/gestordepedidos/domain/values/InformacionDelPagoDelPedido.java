package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

public class InformacionDelPagoDelPedido implements ValueObject<InformacionDelPagoDelPedido.Props> {
    private BigDecimal valorCancelado;
    private Set<String> idDeLaTransacciones;

    public InformacionDelPagoDelPedido(BigDecimal valorCancelado, Set<String> idDeLaTransacciones){
        if(!(valorCancelado.compareTo(new BigDecimal(new BigInteger("0"))) > 0)){
            throw new IllegalArgumentException("El valor pagado no puede ser cero.");
        }
        this.valorCancelado = Objects.requireNonNull(valorCancelado);
        this.idDeLaTransacciones = Objects.requireNonNull(idDeLaTransacciones);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public BigDecimal valorCancelado() {
                return valorCancelado;
            }

            @Override
            public Set<String> idDeLaTransacciones() {
                return idDeLaTransacciones;
            }
        };
    }

    public interface Props{
        BigDecimal valorCancelado();
        Set<String> idDeLaTransacciones();
    }
}
