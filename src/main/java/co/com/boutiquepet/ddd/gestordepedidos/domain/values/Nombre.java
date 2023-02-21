package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<Nombre.Props> {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    public Nombre(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.primerNombre = Objects.requireNonNull(primerNombre);
        this.segundoNombre = Objects.requireNonNull(segundoNombre);
        this.primerApellido = Objects.requireNonNull(primerApellido);
        this.segundoApellido = Objects.requireNonNull(segundoApellido);
    }
    @Override
    public Props value() {
        return new Props() {
            @Override
            public String primerNombre() {
                return primerNombre;
            }

            @Override
            public String segundoNombre() {
                return segundoNombre;
            }

            @Override
            public String primerApellido() {
                return primerApellido;
            }

            @Override
            public String segundoApellido() {
                return segundoApellido();
            }
        };
    }

    interface Props{
        String primerNombre();
        String segundoNombre();
        String primerApellido();
        String segundoApellido();
    }
}
