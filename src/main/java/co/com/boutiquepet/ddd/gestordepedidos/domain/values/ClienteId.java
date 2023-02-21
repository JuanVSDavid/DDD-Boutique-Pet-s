package co.com.boutiquepet.ddd.gestordepedidos.domain.values;


import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Identity;

public class ClienteId extends Identity {
    public ClienteId(String clienteId){
        super(clienteId);
    }
    public ClienteId(){}
    public static ClienteId of(String clienteId){
        return new ClienteId(clienteId);
    }
}
