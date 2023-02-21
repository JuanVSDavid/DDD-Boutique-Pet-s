package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.AggregateRoot;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.AdministradorId;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.Nombre;

import java.util.Set;

public class Administrador extends AggregateRoot<AdministradorId> {
    protected Nombre nombreAdministrador;
    protected Set<Proceso> procesos;

    public Administrador(AdministradorId id, Nombre nombreAdministrador, Set<Proceso> procesos) {
        super(id);
        this.nombreAdministrador = nombreAdministrador;
        this.procesos = procesos;
    }
}
