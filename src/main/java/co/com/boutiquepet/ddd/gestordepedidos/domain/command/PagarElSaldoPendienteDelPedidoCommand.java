package co.com.boutiquepet.ddd.gestordepedidos.domain.command;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Command;

import java.math.BigDecimal;

public class PagarElSaldoPendienteDelPedidoCommand extends Command {
    private String pedidoId;
    private BigDecimal valorCancelado;
    private String idTransaccion;

    public PagarElSaldoPendienteDelPedidoCommand(){}

    public String getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }
}
