package co.com.boutiquepet.ddd.gestordepedidos.domain.command;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Command;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public class CrearPedidoCommand extends Command {
    private String pedidoId;
    private String clienteId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalPedido;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal valorCancelado;
    private String idTransaccion;
    private Set<DetallePedido> detallesPedido;

    public CrearPedidoCommand(){}

    public CrearPedidoCommand(String pedidoId, String clienteId, BigDecimal totalPedido, BigDecimal valorCancelado, String idTransaccion, Set<DetallePedido> detallesPedido) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.totalPedido = totalPedido;
        this.valorCancelado = valorCancelado;
        this.idTransaccion = idTransaccion;
        this.detallesPedido = detallesPedido;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public Set<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public static class DetallePedido{
        private String detallePedidoId;
        private double contornoCuelloCM;
        private double contornoPechoCM;
        private double largoLomoCM;
        private double largoMangaCM;
        private String productoId;
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigDecimal subTotal;
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigInteger cantidad;
        private Boolean esDelStock;

        public DetallePedido(){}

        public DetallePedido(String detallePedidoId, double contornoCuelloCM, double contornoPechoCM, double largoLomoCM, double largoMangaCM, String productoId, BigDecimal subTotal, BigInteger cantidad, Boolean esDelStock) {
            this.detallePedidoId = detallePedidoId;
            this.contornoCuelloCM = contornoCuelloCM;
            this.contornoPechoCM = contornoPechoCM;
            this.largoLomoCM = largoLomoCM;
            this.largoMangaCM = largoMangaCM;
            this.productoId = productoId;
            this.subTotal = subTotal;
            this.cantidad = cantidad;
            this.esDelStock = esDelStock;
        }

        public String getDetallePedidoId() {
            return detallePedidoId;
        }

        public double getContornoCuelloCM() {
            return contornoCuelloCM;
        }

        public double getContornoPechoCM() {
            return contornoPechoCM;
        }

        public double getLargoLomoCM() {
            return largoLomoCM;
        }

        public double getLargoMangaCM() {
            return largoMangaCM;
        }

        public String getProductoId() {
            return productoId;
        }

        public BigDecimal getSubTotal() {
            return subTotal;
        }

        public BigInteger getCantidad() {
            return cantidad;
        }

        public Boolean getEsDelStock() {
            return esDelStock;
        }
    }
}
