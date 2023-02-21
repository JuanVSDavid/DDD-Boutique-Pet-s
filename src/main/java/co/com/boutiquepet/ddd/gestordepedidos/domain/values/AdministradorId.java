package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class AdministradorId extends Identity {
    public AdministradorId(String administradorId){super(administradorId);}
    public AdministradorId(){}
    public static AdministradorId of(String administradorId){
        return new AdministradorId(administradorId);
    }
}
