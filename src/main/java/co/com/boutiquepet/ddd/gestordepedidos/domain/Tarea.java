package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import lombok.Builder;

import java.util.Objects;

public class Tarea extends Entity<TareaId> {
    private TipoTarea tipoTarea;
    private OperadorId operadorId;
    private DetallePedidoId detallePedidoId;
    private EstatusTarea estatusTarea;
    private TiempoTarea tiempoTarea;

    @Builder
    public Tarea(TareaId id, DetallePedidoId detallePedidoId, TipoTarea tipoTarea, EstatusTarea estatusTarea, OperadorId operadorId, TiempoTarea tiempoTarea) {
        super(id);
        this.detallePedidoId = Objects.requireNonNull(detallePedidoId, "No se puede crear una tarea si no tiene un detalle asociado");
        this.estatusTarea = Objects.requireNonNull(estatusTarea);
        this.tipoTarea = Objects.requireNonNull(tipoTarea);
        this.operadorId = operadorId;
        this.tiempoTarea = tiempoTarea;
    }
}
