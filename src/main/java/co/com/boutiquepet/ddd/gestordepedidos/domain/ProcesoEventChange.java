package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.EventChange;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ProcesoCreado;

public class ProcesoEventChange extends EventChange {
    public ProcesoEventChange(Proceso proceso){
        apply((ProcesoCreado event)->{
            proceso.administradorId = event.getAdministradorId();
            proceso.pedidoId = event.getPedidoId();
            proceso.tareas = event.getTareas();
        });
    }
}
