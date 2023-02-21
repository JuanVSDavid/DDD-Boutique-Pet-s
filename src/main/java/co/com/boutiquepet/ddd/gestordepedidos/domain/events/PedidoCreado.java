package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;

import java.util.Set;

public class PedidoCreado extends DomainEvent {
    private PedidoId pedidoId;
    private ClienteId clienteId;
    private TotalPedido totalPedido;
    private Set<DetallePedido> detallePedidos;
    private EstadoPedido estadoPedido;
    private FechaDeCreacionDePedido fechaDeCreacionDePedido;
    private InformacionDelPagoDelPedido informacionDelPagoDelPedido;

    public PedidoCreado(PedidoId pedidoId, ClienteId clienteId, TotalPedido totalPedido, Set<DetallePedido> detallePedidos, InformacionDelPagoDelPedido informacionDelPagoDelPedido) {
        super(PedidoCreado.class.getName());
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.totalPedido = totalPedido;
        if(detallePedidos.isEmpty()){
            throw new IllegalArgumentException("No se puede crear un pedido si no tiene productos comprados.");
        }
        this.detallePedidos = detallePedidos;
        this.estadoPedido = new EstadoPedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido.POR_ASIGNAR);
        this.fechaDeCreacionDePedido = new FechaDeCreacionDePedido();
        this.informacionDelPagoDelPedido = informacionDelPagoDelPedido;
    }

    public PedidoId getPedidoId() {
        return pedidoId;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public TotalPedido getTotalPedido() {
        return totalPedido;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public FechaDeCreacionDePedido getFechaDeCreacionDePedido() {
        return fechaDeCreacionDePedido;
    }

    public InformacionDelPagoDelPedido getInformacionDelPagoDelPedido() {
        return informacionDelPagoDelPedido;
    }
}
