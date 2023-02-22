package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.Pedido;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.ModificarElDetalleDeUnPedidoCommand;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.DetalleDeUnPedidoModificado;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ElEstadoDelPedidoModificadoASaldoPendiente;
import co.com.boutiquepet.ddd.gestordepedidos.domain.events.ElTotalDelPedidoModificado;
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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class ModificarElDetalleDeUnPedidoUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    private ModificarElDetalleDeUnPedidoUseCase useCase;

    @BeforeEach
    void setUp(){useCase = new ModificarElDetalleDeUnPedidoUseCase(repository);}

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
                                .estadoDetallePedido(
                                        ES_DEL_STOCK
                                                ? new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.POR_EMPACAR)
                                                : new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.EN_PROCESO)
                                )
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

        Mono<ModificarElDetalleDeUnPedidoCommand> command = Mono.just(new ModificarElDetalleDeUnPedidoCommand(PEDIDO_ID, DETALLE_PEDIDO_ID, CONTORNO_CUELLO_CM_MODIFICADO, CONTORNO_PECHO_CM_MODIFICADO, LARGO_LOMO_CM_MODIFICADO, LARGO_MANGA_CM_MODIFICADO, PRODUCTO_ID_MODIFICADO, SUBTOTAL_MODIFICADO, CANTIDAD_MODIFICADO, ES_DEL_STOCK_MODIFICADO));

        Mockito.when(repository.findById(PEDIDO_ID)).thenReturn(Flux.just(eventPedido));


        Mockito.when(repository.saveEvent(ArgumentMatchers.any()))
                .thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));

        Flux<DomainEvent> resultado = useCase.apply(command);

        StepVerifier
                .create(resultado)
                .expectNextMatches(domainEvent -> {
                    Assertions.assertEquals(eventPedido.aggregateRootId(), domainEvent.aggregateRootId());
                    Assertions.assertInstanceOf(DetalleDeUnPedidoModificado.class, domainEvent);
                    return true;
                })
                .verifyComplete();
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
                                                : new EstadoDetallePedido(co.com.boutiquepet.ddd.gestordepedidos.domain.enums.EstadoDetallePedido.EN_PROCESO)
                                )
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
        BigInteger CANTIDAD_MODIFICADO = BigInteger.valueOf(2);
        Boolean ES_DEL_STOCK_MODIFICADO = false;

        Mono<ModificarElDetalleDeUnPedidoCommand> command = Mono.just(new ModificarElDetalleDeUnPedidoCommand(PEDIDO_ID, DETALLE_PEDIDO_ID, CONTORNO_CUELLO_CM_MODIFICADO, CONTORNO_PECHO_CM_MODIFICADO, LARGO_LOMO_CM_MODIFICADO, LARGO_MANGA_CM_MODIFICADO, PRODUCTO_ID_MODIFICADO, SUBTOTAL_MODIFICADO, CANTIDAD_MODIFICADO, ES_DEL_STOCK_MODIFICADO));

        Mockito.when(repository.findById(PEDIDO_ID)).thenReturn(Flux.just(eventPedido));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any()))
                .thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));


        Flux<DomainEvent> resultado = useCase.apply(command);

        StepVerifier
                .create(resultado)
                .expectNextMatches(domainEvent -> {
                    Assertions.assertEquals(eventPedido.aggregateRootId(), domainEvent.aggregateRootId());
                    Assertions.assertInstanceOf(DetalleDeUnPedidoModificado.class, domainEvent);
                    return true;
                })
                .expectNextMatches(domainEvent -> {
                    ElTotalDelPedidoModificado elTotalDelPedidoModificado = (ElTotalDelPedidoModificado) domainEvent;
                    // Como yo se que el valor se duplica, lo unico que evaluo es que el valor sea correcto
                    Assertions.assertEquals(eventPedido.getTotalPedido().value().add(SUBTOTAL_MODIFICADO), elTotalDelPedidoModificado.getValorCalculado());
                    Assertions.assertInstanceOf(ElTotalDelPedidoModificado.class, domainEvent);
                    return true;
                })
                .expectNextMatches(domainEvent -> {
                    Assertions.assertInstanceOf(ElEstadoDelPedidoModificadoASaldoPendiente.class, domainEvent);
                    return true;
                })
                .verifyComplete();
    }

}