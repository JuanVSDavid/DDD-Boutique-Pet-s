package co.com.boutiquepet.ddd.gestordepedidos.domain.events;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.exceptions.NotFoundException;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class DetalleDeUnPedidoModificado extends DomainEvent {
    private Set<DetallePedido> detallePedidos;

    public DetalleDeUnPedidoModificado(DetallePedidoId detallePedidoId, Medidas medidas, ProductoId productoId, TotalDetallePedido totalDetallePedido, InformacionDelDetalleDelPedido informacionDelDetalleDelPedido, Set<DetallePedido> detallePedidos){
        super(DetalleDeUnPedidoModificado.class.getName());
        List<DetallePedido> detallePedidosList = new ArrayList<>(detallePedidos);
        int indiceDelDetalle =
                IntStream
                        .range(0, detallePedidosList.size())
                        .filter(i -> detallePedidoId.value().equals(detallePedidosList.get(i).identity().value()))
                        .findFirst()
                        .orElse(-1);
        if(!(indiceDelDetalle >= 0)){
            throw new NotFoundException("No se puede modificar el detalle si no existe dentro del pedido");
        }
        detallePedidosList.get(indiceDelDetalle).validarSiElDetalleDelPedidoPuedeModificarse();
        detallePedidosList.set(indiceDelDetalle,
                DetallePedido
                        .builder()
                        .id(detallePedidoId)
                        .medidas(medidas)
                        .productoId(productoId)
                        .totalDetallePedido(totalDetallePedido)
                        .informacionDelDetalleDelPedido(informacionDelDetalleDelPedido)
                        .estadoDetallePedido(
                                informacionDelDetalleDelPedido.value().esDelStock()
                                        ? new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_EMPACAR)
                                        : new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_ASIGNAR)
                        )
                        .build()
                );
        this.detallePedidos = new HashSet<>(detallePedidosList);
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
}
