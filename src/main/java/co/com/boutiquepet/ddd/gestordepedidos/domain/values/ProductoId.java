package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class ProductoId extends Identity {
    public ProductoId(String productoId){
        super(productoId);
    }
    public ProductoId(){}
    public static ProductoId of(String productoId){
        return new ProductoId(productoId);
    }
}
