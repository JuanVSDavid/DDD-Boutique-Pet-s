package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class TareaId extends Identity {
    public TareaId(String tareaId){super(tareaId);}
    public TareaId(){}
    public static TareaId of(String tareaId){return new TareaId(tareaId);}
}
