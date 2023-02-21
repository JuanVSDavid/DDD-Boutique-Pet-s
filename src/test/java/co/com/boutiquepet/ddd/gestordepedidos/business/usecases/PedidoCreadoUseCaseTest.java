package co.com.boutiquepet.ddd.gestordepedidos.business.usecases;

import co.com.boutiquepet.ddd.gestordepedidos.business.gateways.DomainEventRepository;
import co.com.boutiquepet.ddd.gestordepedidos.business.generic.DomainEvent;
import co.com.boutiquepet.ddd.gestordepedidos.domain.command.CrearPedidoCommand;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoCreadoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    private CrearPedidoUseCase useCase;

    @BeforeEach
    void setUp(){useCase = new CrearPedidoUseCase(repository);}

    @Test
    void successfulScenario(){
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

        Set<CrearPedidoCommand.DetallePedido> DETALLES_PEDIDO = new HashSet<>();
        DETALLES_PEDIDO.add(new CrearPedidoCommand.DetallePedido(DETALLE_PEDIDO_ID, CONTORNO_CUELLO_CM, CONTORNO_PECHO_CM, LARGO_LOMO_CM, LARGO_MANGA_CM, PRODUCTO_ID, SUBTOTAL, CANTIDAD, ES_DEL_STOCK));

        Mono<CrearPedidoCommand> command = Mono.just(new CrearPedidoCommand(PEDIDO_ID, CLIENTE_ID, TOTAL_PEDIDO, VALOR_CANCELADO, ID_TRANSACCION, DETALLES_PEDIDO));

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

        PedidoCreado event = new PedidoCreado(PedidoId.of(PEDIDO_ID), ClienteId.of(CLIENTE_ID), new TotalPedido(TOTAL_PEDIDO), DETALLES_PEDIDO_EVENT, new InformacionDelPagoDelPedido(VALOR_CANCELADO, ID_TRANSACCIONES));
        event.setAggregateRootId(PEDIDO_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(PedidoCreado.class)))
                .thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));

        Flux<DomainEvent> resultado = useCase.apply(command);

        StepVerifier.create(resultado)
                .expectNextMatches(domainEvent -> {
                    Assertions.assertEquals(event.aggregateRootId(), domainEvent.aggregateRootId());
                    Assertions.assertInstanceOf(PedidoCreado.class, domainEvent);
                    return true;
                })
                .verifyComplete();

        Mockito.verify(repository,Mockito.times(1)).saveEvent(ArgumentMatchers.any(PedidoCreado.class));
    }

}