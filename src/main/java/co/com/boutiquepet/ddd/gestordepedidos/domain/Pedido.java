package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.AggregateRoot;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.*;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Pedido extends AggregateRoot<PedidoId> {
    protected ClienteId clienteId;
    protected TotalPedido totalPedido;
    protected Set<DetallePedido> detallePedidos;
    protected EstadoPedido estadoPedido;
    protected FechaDeCreacionDePedido fechaDeCreacionDePedido;
    protected InformacionDelPagoDelPedido informacionDelPagoDelPedido;
    protected ProcesoId procesoId;

    public Pedido(PedidoId id, ClienteId clienteId, TotalPedido totalPedido, Set<DetallePedido> detallePedidos, InformacionDelPagoDelPedido informacionDelPagoDelPedido) {
        super(id);
        subscribe(new PedidoEventChange(this));
        appendChange(new PedidoCreado(id, clienteId, totalPedido, detallePedidos, informacionDelPagoDelPedido)).apply();
    }

    public static Pedido from(PedidoId pedidoId, List<DomainEvent> events) {
        var pedido = new Pedido(pedidoId);
        events.forEach(pedido::applyEvent);
        return pedido;
    }

    private Pedido(PedidoId id) {
        super(id);
        subscribe(new PedidoEventChange(this));
    }

    public void modificarElDetalle(DetallePedidoId id, Medidas medidas, ProductoId productoId, TotalDetallePedido totalDetallePedido, InformacionDelDetalleDelPedido informacionDelDetalleDelPedido){
        appendChange(new DetalleDeUnPedidoModificado(id, medidas, productoId, totalDetallePedido, informacionDelDetalleDelPedido, this.detallePedidos)).apply();
    }

    public void calcularElValorTotalDelPedido(){
        BigDecimal total = detallePedidos.stream().reduce(new BigDecimal("0"), (partialResult, detallePedido) ->
            partialResult.add(detallePedido.obtenerElTotalDelDetalle()), BigDecimal::add);
        if(total.compareTo(this.totalPedido.value()) != 0){
            appendChange(new ElTotalDelPedidoModificado(total)).apply();
            if(this.informacionDelPagoDelPedido.value().valorCancelado().compareTo(total) < 0){
                appendChange(new ElEstadoDelPedidoModificadoASaldoPendiente(this.detallePedidos)).apply();
            }else{
                appendChange(new ElEstadoDelPedidoModificadoASaldoADevolver()).apply();
            }
        }
    }

    public void agregarElProcesoAsociadoAlPedido(ProcesoId procesoId){
        appendChange(new ElProcesoAsociadoAlPedidoFueAgregado(procesoId)).apply();
    }

    public void verificarSiElPedidoPuedeModificarse(){
        validacionDelPedido("El pedido ya no se puede modificar.", 2);
    }

    public void pagarElValorDelSaldoPendiente(BigDecimal valorCancelado, String idDeLaTransaccion){
        appendChange(new PagadoElSaldoPendienteDelPedido(valorCancelado, idDeLaTransaccion, this.informacionDelPagoDelPedido, this.procesoId)).apply();
    }

    public void cancelarPedido(){
        appendChange(new PedidoCancelado(this.detallePedidos)).apply();
    }

    public void verificarSiElPedidoPuedeSerCancelado() {
        validacionDelPedido("El pedido no se puede cancelar.", 3);
    }

    private void validacionDelPedido(String mensaje, Integer Dias){
        Date fechaDeCreacionDePedido = Date.from(this.fechaDeCreacionDePedido.value());
        Date fechaActual = Date.from(Instant.now());
        Duration duration = Duration.between(Instant.now(), this.fechaDeCreacionDePedido.value());
        int diasTranscurridosDespuesDeLaCreacionDelPedido =
                (int) (duration.getSeconds() / 86400);
        if(
                (fechaActual.compareTo(fechaDeCreacionDePedido) > 1 && diasTranscurridosDespuesDeLaCreacionDelPedido > Dias)
                        || this.estadoPedido.value().equals(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido.POR_ENVIAR)
                        || this.estadoPedido.value().equals(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoPedido.ENVIADO)
        ){
            throw new IllegalArgumentException(mensaje);
        }
    }
}
