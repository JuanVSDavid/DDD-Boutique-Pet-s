package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class DetallePedidoId extends Identity {
    public DetallePedidoId(String detallePedidoId){
        super(detallePedidoId);
    }
    public DetallePedidoId(){}
    public static DetallePedidoId of(String detallePedidoId){
        return new DetallePedidoId(detallePedidoId);
    }
}
