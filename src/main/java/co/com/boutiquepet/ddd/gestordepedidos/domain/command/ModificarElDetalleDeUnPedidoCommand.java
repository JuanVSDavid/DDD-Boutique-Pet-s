package co.com.boutiquepet.ddd.gestordepedidos.domain.command;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.Command;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ModificarElDetalleDeUnPedidoCommand extends Command {
    private String pedidoId;
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

    public ModificarElDetalleDeUnPedidoCommand(){}

    public ModificarElDetalleDeUnPedidoCommand(String pedidoId, String detallePedidoId, double contornoCuelloCM, double contornoPechoCM, double largoLomoCM, double largoMangaCM, String productoId, BigDecimal subTotal, BigInteger cantidad, Boolean esDelStock) {
        this.pedidoId = pedidoId;
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

    public String getPedidoId() {
        return pedidoId;
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
