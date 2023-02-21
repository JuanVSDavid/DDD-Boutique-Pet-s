package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class PedidoId extends Identity {
    public PedidoId(String pedidoId){
        super(pedidoId);
    }
    public PedidoId(){}
    public static PedidoId of(String pedidoId){
        return new PedidoId(pedidoId);
    }
}
