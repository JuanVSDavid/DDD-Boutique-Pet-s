package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.ProcesoId;

public class ElProcesoAsociadoAlPedidoFueAgregado extends DomainEvent {
    private ProcesoId procesoId;
    public ElProcesoAsociadoAlPedidoFueAgregado(ProcesoId procesoId) {
        super(ElProcesoAsociadoAlPedidoFueAgregado.class.getName());
        this.procesoId = procesoId;
    }

    public ProcesoId getProcesoId() {
        return procesoId;
    }
}
