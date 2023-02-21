package co.com.boutiquepet.ddd.gestordepedidos.domain.enums;

import java.io.Serializable;

public enum EstadoDetallePedido implements Serializable {
    POR_ASIGNAR,
    EN_PAUSA,
    EN_PROCESO,
    POR_EMPACAR,
    TERMINADO,
    CANCELADO
}
