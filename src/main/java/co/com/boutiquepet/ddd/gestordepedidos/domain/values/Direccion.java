package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;
import co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoDireccion;

import java.util.Objects;

public class Direccion implements ValueObject<Direccion.Props> {
    private String direccion;
    private String ciudad;
    private String departamento;
    private TipoDireccion tipoDireccion;

    public Direccion(String direccion, String ciudad, String departamento, TipoDireccion tipoDireccion) {
        this.direccion = Objects.requireNonNull(direccion);
        this.ciudad = Objects.requireNonNull(ciudad);
        this.departamento = Objects.requireNonNull(departamento);
        this.tipoDireccion = Objects.requireNonNull(tipoDireccion);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String direccion() {
                return direccion;
            }

            @Override
            public String ciudad() {
                return ciudad;
            }

            @Override
            public String departamento() {
                return departamento;
            }

            @Override
            public TipoDireccion tipoDireccion() {
                return tipoDireccion;
            }
        };
    }

    interface Props{
        String direccion();
        String ciudad();
        String departamento();
        TipoDireccion tipoDireccion();
    }
}
