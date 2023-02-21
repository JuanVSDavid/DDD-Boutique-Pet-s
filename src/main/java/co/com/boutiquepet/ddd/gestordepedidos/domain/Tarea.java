package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.TareaId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.TipoTarea;

public class Tarea extends Entity<TareaId> {
    private TipoTarea tipoTarea;

    public Tarea(TareaId id) {
        super(id);
    }
}
