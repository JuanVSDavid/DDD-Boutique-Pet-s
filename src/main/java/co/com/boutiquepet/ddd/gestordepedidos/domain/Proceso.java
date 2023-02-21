package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.AggregateRoot;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.AdministradorId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.PedidoId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.ProcesoId;

import java.util.Set;

public class Proceso extends Entity<ProcesoId> {
    private PedidoId pedidoId;
    private Set<Tarea> tareas;
    private AdministradorId administradorId;
    public Proceso(ProcesoId id, PedidoId pedidoId, Set<Tarea> tareas, AdministradorId administradorId) {
        super(id);
        this.pedidoId = pedidoId;
        this.tareas = tareas;
        this.administradorId = administradorId;
    }
}
