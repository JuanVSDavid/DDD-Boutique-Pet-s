package co.com.boutiquepet.ddd.gestordepedidos.domain;



import co.com.boutiquepet.ddd.gestordepedidos.business.generic.AggregateRoot;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;

import java.util.Set;

public class Cliente extends AggregateRoot<ClienteId> {
    protected Nombre nombre;
    protected Set<PedidoId> pedidos;
    protected Direccion direccion;
    protected ContactoCliente contactoCliente;

    public Cliente(ClienteId id, Nombre nombre, Direccion direccion, ContactoCliente contactoCliente) {
        super(id);
        this.nombre = nombre;
        this.direccion = direccion;
        this.contactoCliente = contactoCliente;
    }
}
