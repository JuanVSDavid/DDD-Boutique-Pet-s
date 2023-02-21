package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventNoReactivoRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.ModificarElDetalleDeUnPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ElEstadoDelPedidoModificadoASaldoPendiente;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ElTotalDelPedidoModificado;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.DetalleDeUnPedidoModificado;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.PedidoCreado;
import co.com.boutiquepet.ddd.gestordepedidos.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ModificarElDetalleDeUnPedidoNoReactivoUseCaseTest {
    @Mock
    private DomainEventNoReactivoRepository repository;
    private ModificarElDetalleDeUnPedidoNoReactivoUseCase useCase;

    @BeforeEach
    void setUp(){
        useCase = new ModificarElDetalleDeUnPedidoNoReactivoUseCase(repository);
    }

    @Test
    void successfulScenarioModificacionSinCambioDePrecio(){
        String PEDIDO_ID = "pedidoIdCorrecto";
        String CLIENTE_ID = "clienteIdReal";
        BigDecimal TOTAL_PEDIDO = BigDecimal.valueOf(200000.0);
        BigDecimal VALOR_CANCELADO = BigDecimal.valueOf(200000.0);
        String ID_TRANSACCION = "IdTransaccionMuyReal";
        Set<String> ID_TRANSACCIONES = new HashSet<>();
        ID_TRANSACCIONES.add(ID_TRANSACCION);

        String DETALLE_PEDIDO_ID = "detalleIdCorrecto";
        double CONTORNO_CUELLO_CM = 20;
        double CONTORNO_PECHO_CM = 20;
        double LARGO_LOMO_CM = 20;
        double LARGO_MANGA_CM = 20;
        String PRODUCTO_ID = "productoIdReal";
        BigDecimal SUBTOTAL = BigDecimal.valueOf(100000.0);
        BigInteger CANTIDAD = BigInteger.valueOf(2);
        Boolean ES_DEL_STOCK = false;
        Set<co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido> DETALLES_PEDIDO_EVENT = new HashSet<>();

        DETALLES_PEDIDO_EVENT
                .add(
                        co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido
                                .builder()
                                .productoId(ProductoId.of(PRODUCTO_ID))
                                .medidas(new Medidas(CONTORNO_CUELLO_CM, CONTORNO_PECHO_CM, LARGO_LOMO_CM, LARGO_MANGA_CM))
                                .totalDetallePedido(new TotalDetallePedido(SUBTOTAL, CANTIDAD))
                                .informacionDelDetalleDelPedido(new InformacionDelDetalleDelPedido(ES_DEL_STOCK))
                                .id(DetallePedidoId.of(DETALLE_PEDIDO_ID))
                                .build()
                );

        PedidoCreado eventPedido = new PedidoCreado(PedidoId.of(PEDIDO_ID), ClienteId.of(CLIENTE_ID), new TotalPedido(TOTAL_PEDIDO), DETALLES_PEDIDO_EVENT, new InformacionDelPagoDelPedido(VALOR_CANCELADO, ID_TRANSACCIONES));
        eventPedido.setAggregateRootId(PEDIDO_ID);

        double CONTORNO_CUELLO_CM_MODIFICADO = 20;
        double CONTORNO_PECHO_CM_MODIFICADO = 20;
        double LARGO_LOMO_CM_MODIFICADO = 20;
        double LARGO_MANGA_CM_MODIFICADO = 20;
        String PRODUCTO_ID_MODIFICADO = "productoIdRealModificado";
        BigDecimal SUBTOTAL_MODIFICADO = BigDecimal.valueOf(200000.0);
        BigInteger CANTIDAD_MODIFICADO = BigInteger.valueOf(1);
        Boolean ES_DEL_STOCK_MODIFICADO = false;

        ModificarElDetalleDeUnPedidoCommand command = new ModificarElDetalleDeUnPedidoCommand(PEDIDO_ID, DETALLE_PEDIDO_ID, CONTORNO_CUELLO_CM_MODIFICADO, CONTORNO_PECHO_CM_MODIFICADO, LARGO_LOMO_CM_MODIFICADO, LARGO_MANGA_CM_MODIFICADO, PRODUCTO_ID_MODIFICADO, SUBTOTAL_MODIFICADO, CANTIDAD_MODIFICADO, ES_DEL_STOCK_MODIFICADO);

        Mockito.when(repository.findById(PEDIDO_ID))
                .thenReturn(List.of(eventPedido));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(DetalleDeUnPedidoModificado.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(command.getPedidoId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(DetalleDeUnPedidoModificado.class, result.get(0));

        Mockito.verify(repository, Mockito.times(1)).saveEvent(Mockito.any());
    }

    @Test
    void successfulScenarioModificacionConCambioDePrecio(){
        String PEDIDO_ID = "pedidoIdCorrecto";
        String CLIENTE_ID = "clienteIdReal";
        BigDecimal TOTAL_PEDIDO = BigDecimal.valueOf(200000.0);
        BigDecimal VALOR_CANCELADO = BigDecimal.valueOf(200000.0);
        String ID_TRANSACCION = "IdTransaccionMuyReal";
        Set<String> ID_TRANSACCIONES = new HashSet<>();
        ID_TRANSACCIONES.add(ID_TRANSACCION);

        String DETALLE_PEDIDO_ID = "detalleIdCorrecto";
        double CONTORNO_CUELLO_CM = 20;
        double CONTORNO_PECHO_CM = 20;
        double LARGO_LOMO_CM = 20;
        double LARGO_MANGA_CM = 20;
        String PRODUCTO_ID = "productoIdReal";
        BigDecimal SUBTOTAL = BigDecimal.valueOf(100000.0);
        BigInteger CANTIDAD = BigInteger.valueOf(2);
        Boolean ES_DEL_STOCK = false;

        Set<co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido> DETALLES_PEDIDO_EVENT = new HashSet<>();

        DETALLES_PEDIDO_EVENT
                .add(
                        co.com.boutiquepet.ddd.gestordepedidos.domain.DetallePedido
                                .builder()
                                .productoId(ProductoId.of(PRODUCTO_ID))
                                .medidas(new Medidas(CONTORNO_CUELLO_CM, CONTORNO_PECHO_CM, LARGO_LOMO_CM, LARGO_MANGA_CM))
                                .totalDetallePedido(new TotalDetallePedido(SUBTOTAL, CANTIDAD))
                                .informacionDelDetalleDelPedido(new InformacionDelDetalleDelPedido(ES_DEL_STOCK))
                                .id(DetallePedidoId.of(DETALLE_PEDIDO_ID))
                                .estadoDetallePedido(
                                        ES_DEL_STOCK
                                                ? new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_EMPACAR)
                                                : new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_ASIGNAR)
                                )
                                .build()
                );

        PedidoCreado eventPedido = new PedidoCreado(PedidoId.of(PEDIDO_ID), ClienteId.of(CLIENTE_ID), new TotalPedido(TOTAL_PEDIDO), DETALLES_PEDIDO_EVENT, new InformacionDelPagoDelPedido(VALOR_CANCELADO, ID_TRANSACCIONES));
        eventPedido.setAggregateRootId(PEDIDO_ID);

        double CONTORNO_CUELLO_CM_MODIFICADO = 20;
        double CONTORNO_PECHO_CM_MODIFICADO = 30;
        double LARGO_LOMO_CM_MODIFICADO = 45;
        double LARGO_MANGA_CM_MODIFICADO = 15;
        String PRODUCTO_ID_MODIFICADO = "productoIdRealModificado";
        BigDecimal SUBTOTAL_MODIFICADO = BigDecimal.valueOf(200000.0);
        BigInteger CANTIDAD_MODIFICADO = BigInteger.valueOf(2);
        Boolean ES_DEL_STOCK_MODIFICADO = false;

        ModificarElDetalleDeUnPedidoCommand command = new ModificarElDetalleDeUnPedidoCommand(PEDIDO_ID, DETALLE_PEDIDO_ID, CONTORNO_CUELLO_CM_MODIFICADO, CONTORNO_PECHO_CM_MODIFICADO, LARGO_LOMO_CM_MODIFICADO, LARGO_MANGA_CM_MODIFICADO, PRODUCTO_ID_MODIFICADO, SUBTOTAL_MODIFICADO, CANTIDAD_MODIFICADO, ES_DEL_STOCK_MODIFICADO);

        Mockito.when(repository.findById(PEDIDO_ID))
                .thenReturn(List.of(eventPedido));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(command.getPedidoId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(DetalleDeUnPedidoModificado.class, result.get(0));
        Assertions.assertInstanceOf(ElTotalDelPedidoModificado.class, result.get(1));
        Assertions.assertInstanceOf(ElEstadoDelPedidoModificadoASaldoPendiente.class, result.get(2));

        Mockito.verify(repository, Mockito.times(3)).saveEvent(Mockito.any());
    }
}