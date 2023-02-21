package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.time.Instant;

public class FechaDeCreacionDePedido implements ValueObject<Instant> {
    Instant fechaDeCreacionDePedido;
    public FechaDeCreacionDePedido(){
        fechaDeCreacionDePedido = Instant.now();
    }
    @Override
    public Instant value() {
        return fechaDeCreacionDePedido;
    }
}
