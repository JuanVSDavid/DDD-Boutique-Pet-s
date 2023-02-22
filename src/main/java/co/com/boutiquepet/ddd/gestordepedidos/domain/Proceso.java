package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.AggregateRoot;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoTarea;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ProcesoCreado;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Proceso extends AggregateRoot<ProcesoId> {
    protected PedidoId pedidoId;
    protected Set<Tarea> tareas;
    protected AdministradorId administradorId;
    @Builder
    public Proceso(ProcesoId id, PedidoId pedidoId, Set<Tarea> tareas, AdministradorId administradorId) {
        super(id);
        subscribe(new ProcesoEventChange(this));
        appendChange(new ProcesoCreado(id, pedidoId, tareas, administradorId)).apply();
    }

    public static Set<Tarea> crearTareas(Set<DetallePedido> detallePedidos){
        return detallePedidos.stream().flatMap(detallePedido -> {
            List<Tarea> tareas = new ArrayList<>();
            for (TipoTarea tipoTarea:
                    TipoTarea.values()) {
                tareas.add(
                        Tarea
                                .builder()
                                .tipoTarea(new co.com.boutiquepet.ddd.gestordepedidos.domain.values.TipoTarea(tipoTarea))
                                .detallePedidoId(detallePedido.identity())
                                .estatusTarea(new EstatusTarea(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstatusTarea.POR_ASIGNAR))
                                .tiempoTarea(new TiempoTarea(LocalDateTime.now(), null, null))
                                .operadorId(null)
                                .id(TareaId.of(java.util.UUID.randomUUID().toString()))
                                .build()
                );
            }
            return tareas.stream();
        }).collect(Collectors.toSet());
    }
}
