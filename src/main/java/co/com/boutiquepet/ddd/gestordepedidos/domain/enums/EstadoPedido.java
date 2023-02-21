package co.com.boutiquepet.ddd.gestordepedidos.domain.enums;

import java.io.Serializable;

public enum EstadoPedido implements Serializable {
    CANCELADO,
    PENDIENTE_SALDO,
    EN_DEVOLUCION,
    POR_ASIGNAR,
    EN_PROCESO,
    POR_ENVIAR,
    ENVIADO
}
