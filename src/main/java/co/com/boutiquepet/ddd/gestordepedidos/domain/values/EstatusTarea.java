package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class EstatusTarea implements ValueObject<co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstatusTarea> {
    private co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstatusTarea estatusTarea;

    public EstatusTarea(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstatusTarea estatusTarea){
        this.estatusTarea = Objects.requireNonNull(estatusTarea);
    }

    @Override
    public co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstatusTarea value() {
        return this.estatusTarea;
    }
}
