package co.com.boutiquepet.ddd.gestordepedidos.domain.enums;

import java.io.Serializable;

public enum EstadoDetallePedido implements Serializable {
    EN_PAUSA,
    EN_PROCESO,
    POR_EMPACAR,
    TERMINADO,
    CANCELADO
}
