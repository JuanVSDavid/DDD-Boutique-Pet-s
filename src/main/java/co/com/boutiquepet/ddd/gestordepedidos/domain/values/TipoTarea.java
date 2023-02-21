package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class TipoTarea implements ValueObject<co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoTarea> {
    private co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoTarea tipoTarea;
    public TipoTarea(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoTarea tipoTarea){
        this.tipoTarea = Objects.requireNonNull(tipoTarea);
    }

    @Override
    public co.com.boutiquepet.ddd.gestordepedidos.domain.enums.TipoTarea value() {
        return tipoTarea;
    }
}
