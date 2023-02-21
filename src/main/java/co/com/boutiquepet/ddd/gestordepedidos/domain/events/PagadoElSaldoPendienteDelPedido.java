package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.InformacionDelPagoDelPedido;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PagadoElSaldoPendienteDelPedido extends DomainEvent {
    private InformacionDelPagoDelPedido informacionDelPagoDelPedido;
    public PagadoElSaldoPendienteDelPedido(BigDecimal valorCancelado, String idDeLaTransaccion, InformacionDelPagoDelPedido informacionDelPagoDelPedido) {
        super(PagadoElSaldoPendienteDelPedido.class.getName());
        Set<String> idDeLasTransacciones = new HashSet<>(informacionDelPagoDelPedido.value().idDeLaTransacciones());
        idDeLasTransacciones.add(idDeLaTransaccion);
        this.informacionDelPagoDelPedido =  new InformacionDelPagoDelPedido
                (
                        informacionDelPagoDelPedido.value().valorCancelado().add(valorCancelado),
                        idDeLasTransacciones
                );
    }

    public InformacionDelPagoDelPedido getInformacionDelPagoDelPedido() {
        return informacionDelPagoDelPedido;
    }
}
