package co.com.boutiquepet.ddd.gestordepedidos.domain;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.EventChange;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.*;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.TotalPedido;

public class PedidoEventChange extends EventChange {

    public PedidoEventChange(Pedido pedido) {
        apply((PedidoCreado event) -> {
            pedido.detallePedidos = event.getDetallePedidos();
            pedido.estadoPedido = event.getEstadoPedido();
            pedido.totalPedido = event.getTotalPedido();
            pedido.clienteId = event.getClienteId();
            pedido.informacionDelPagoDelPedido = event.getInformacionDelPagoDelPedido();
            pedido.fechaDeCreacionDePedido = event.getFechaDeCreacionDePedido();
        });
        apply((DetalleDeUnPedidoModificado event)->{
            pedido.verificarSiElPedidoPuedeModificarse();
            pedido.detallePedidos = event.getDetallePedidos();
            pedido.calcularElValorTotalDelPedido();
        });
        apply((ElTotalDelPedidoModificado event)->{
            pedido.totalPedido = new TotalPedido(event.getValorCalculado());
        });
        apply((ElEstadoDelPedidoModificadoASaldoPendiente event)->{
            pedido.estadoPedido = event.getEstadoPedido();
            pedido.detallePedidos = event.getDetallePedidos();
        });
        apply((PagadoElSaldoPendienteDelPedido event)->{
            pedido.informacionDelPagoDelPedido = event.getInformacionDelPagoDelPedido();
            // Ejecutar un función que se relacione con procesos para evaluar cada una de las tareas y que actualicen su estado al pertienente
        });
        apply((PedidoCancelado event) -> {
            pedido.verificarSiElPedidoPuedeSerCancelado();
            pedido.estadoPedido = event.getEstadoPedido();
            pedido.detallePedidos = event.getDetallePedidos();
            // Ejecutar acciones para actualizar el estado del proceso, devolver el dinero pagado
        });
        apply((ElEstadoDelPedidoModificadoASaldoADevolver event)->{
            pedido.estadoPedido = event.getEstadoPedido();
        });
        apply((ElProcesoAsociadoAlPedidoFueAgregado event)->{
            pedido.procesoId = event.getProcesoId();
        });
    }
}
