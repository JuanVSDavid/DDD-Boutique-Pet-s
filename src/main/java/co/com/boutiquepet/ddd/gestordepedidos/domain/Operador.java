package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.Nombre;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.OperadorId;

public class Operador extends Entity<OperadorId> {
    private Nombre nombre;

    public Operador(OperadorId id) {
        super(id);
    }
}
