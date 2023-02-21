package co.com.boutiquepet.ddd.gestordepedidos.business.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
