package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.InformacionDelPagoDelPedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.ProcesoId;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PagadoElSaldoPendienteDelPedido extends DomainEvent {
    private InformacionDelPagoDelPedido informacionDelPagoDelPedido;
    private ProcesoId procesoId;
    public PagadoElSaldoPendienteDelPedido(BigDecimal valorCancelado, String idDeLaTransaccion, InformacionDelPagoDelPedido informacionDelPagoDelPedido, ProcesoId procesoId) {
        super(PagadoElSaldoPendienteDelPedido.class.getName());
        Set<String> idDeLasTransacciones = new HashSet<>(informacionDelPagoDelPedido.value().idDeLaTransacciones());
        idDeLasTransacciones.add(idDeLaTransaccion);
        this.informacionDelPagoDelPedido =  new InformacionDelPagoDelPedido
                (
                        informacionDelPagoDelPedido.value().valorCancelado().add(valorCancelado),
                        idDeLasTransacciones
                );
        this.procesoId = procesoId;
    }

    public ProcesoId getProcesoId() {
        return procesoId;
    }
    public InformacionDelPagoDelPedido getInformacionDelPagoDelPedido() {
        return informacionDelPagoDelPedido;
    }
}
