package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TiempoTarea implements ValueObject<TiempoTarea.Props> {
    private final String ZonaHoraria = "UTC";

    private ZonedDateTime fechaYHoraDeCreacion;
    private ZonedDateTime fechaYHoraDeInicio;
    private ZonedDateTime fechaYHoraDeFinalizacion;

    public TiempoTarea(LocalDateTime fechaYHoraDeCreacion, LocalDateTime fechaYHoraDeInicio, LocalDateTime fechaYHoraDeFinalizacion){
        this.fechaYHoraDeCreacion = ZonedDateTime.of(Objects.requireNonNull(fechaYHoraDeCreacion), ZoneId.of(ZonaHoraria));
        if(Objects.isNull(fechaYHoraDeFinalizacion)){
            this.fechaYHoraDeInicio = null;
        }else{
            this.fechaYHoraDeInicio = ZonedDateTime.of(fechaYHoraDeInicio, ZoneId.of(ZonaHoraria));
        }
        if(Objects.isNull(fechaYHoraDeFinalizacion)){
            this.fechaYHoraDeFinalizacion = null;
        }else{
            this.fechaYHoraDeFinalizacion = ZonedDateTime.of(fechaYHoraDeFinalizacion, ZoneId.of(ZonaHoraria));
        }
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public ZonedDateTime fechaYHoraDeInicio() {
                return fechaYHoraDeInicio;
            }

            @Override
            public ZonedDateTime fechaYHoraDeFinalizacion() {
                return fechaYHoraDeFinalizacion;
            }
        };
    }

    public interface Props{
        ZonedDateTime fechaYHoraDeInicio();
        ZonedDateTime fechaYHoraDeFinalizacion();
    }
}
