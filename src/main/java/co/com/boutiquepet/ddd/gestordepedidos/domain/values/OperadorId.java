package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class OperadorId extends Identity {
    public OperadorId(String operadorId){super(operadorId);}
    public OperadorId(){}
    public static OperadorId of(String operadorId){return new OperadorId(operadorId);}
}
