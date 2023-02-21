package co.com.boutiquepet.ddd.gestordepedidos.domain.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
