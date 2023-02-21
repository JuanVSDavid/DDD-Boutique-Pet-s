package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class ProcesoId extends Identity {
    public ProcesoId(String procesoId){
        super(procesoId);
    }
    public ProcesoId(){}
    public static ProcesoId of(String procesoId){
        return new ProcesoId(procesoId);
    }
}
