package co.com.boutiquepet.ddd.gestordepedidos.business.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public abstract class UseCaseForCommandNoReactivo <R extends Command> implements Function<R, List<DomainEvent>> {
    public abstract List<DomainEvent> apply(R command);
}
