package co.com.boutiquepet.ddd.gestordepedidos.domain.command;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Command;

public class CancelarPedidoCommand extends Command {
    private String pedidoId;

    public CancelarPedidoCommand() {
    }

    public String getPedidoId() {
        return pedidoId;
    }
}
