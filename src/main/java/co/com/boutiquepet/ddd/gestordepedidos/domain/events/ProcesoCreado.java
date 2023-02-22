package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Tarea;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.AdministradorId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.PedidoId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.ProcesoId;

import java.util.Objects;
import java.util.Set;

public class ProcesoCreado extends DomainEvent {
    private ProcesoId procesoId;
    private PedidoId pedidoId;
    private Set<Tarea> tareas;
    private AdministradorId administradorId;

    public ProcesoCreado(ProcesoId id, PedidoId pedidoId, Set<Tarea> tareas, AdministradorId administradorId) {
        super(ProcesoCreado.class.getName());
        this.procesoId = id;
        this.pedidoId = pedidoId;
        this.tareas = tareas;
        this.administradorId = administradorId;
    }

    public ProcesoId getProcesoId() {
        return procesoId;
    }

    public PedidoId getPedidoId() {
        return pedidoId;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public AdministradorId getAdministradorId() {
        return administradorId;
    }
}
