package co.com.boutiquepet.ddd.gestordepedidos.domain;


import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Entity;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import lombok.Builder;

import java.math.BigDecimal;
public class DetallePedido extends Entity<DetallePedidoId> {
    private DetallePedidoId id;
    private Medidas medidas;
    private ProductoId productoId;
    private TotalDetallePedido totalDetallePedido;
    private EstadoDetallePedido estadoDetallePedido;
    private InformacionDelDetalleDelPedido informacionDelDetalleDelPedido;

    @Builder
    public DetallePedido(DetallePedidoId id, Medidas medidas, ProductoId productoId, TotalDetallePedido totalDetallePedido, InformacionDelDetalleDelPedido informacionDelDetalleDelPedido, EstadoDetallePedido estadoDetallePedido) {
        super(id);
        this.medidas = medidas;
        this.productoId = productoId;
        this.totalDetallePedido = totalDetallePedido;
        this.informacionDelDetalleDelPedido = informacionDelDetalleDelPedido;
        this.estadoDetallePedido = estadoDetallePedido;
    }

    public BigDecimal obtenerElTotalDelDetalle(){
        return totalDetallePedido.value().total();
    }

    public void validarSiElDetalleDelPedidoPuedeModificarse(){
        Boolean esDelStock = informacionDelDetalleDelPedido.value().esDelStock();
        co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido estado = estadoDetallePedido.value();
        if(
                !esDelStock
                        && (estado.equals(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.TERMINADO)
                        || estado.equals(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.EN_PROCESO)
                        || estado.equals(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_EMPACAR))
        ){
            throw new IllegalArgumentException("El detalle del pedido no se puede modificar");
        }
    }
    public void pasarACanceladoElDetallePorCancelacionDePedido(){
        pasarElDetalleAUnEstado(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.CANCELADO);
    }

    public void pasarAPausaElDetallePorSaldoPendiente(){
        pasarElDetalleAUnEstado(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.EN_PAUSA);
    }

    private void pasarElDetalleAUnEstado(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido estadoDetallePedido){
        this.estadoDetallePedido = new EstadoDetallePedido(estadoDetallePedido);
    }
}
