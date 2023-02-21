package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactoCliente implements ValueObject<ContactoCliente.Props> {
    private String numeroTelefonico;
    private String correoElectronico;

    public ContactoCliente(String numeroTelefonico, String correoElectronico){
        Pattern patternTelefono = Pattern.compile("^3[0-9]{9}$");
        Matcher matcherTelefono = patternTelefono.matcher(numeroTelefonico);
        Pattern patternCorreoElectronico = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher matcherCorreoElectronico = patternCorreoElectronico.matcher(correoElectronico);
        if(!matcherTelefono.matches()){
            throw new IllegalArgumentException("No es un número teléfonico valido.");
        }
        if(!matcherCorreoElectronico.matches()){
            throw new IllegalArgumentException("No es un correo electronico valido.");
        }
        this.correoElectronico = correoElectronico;
        this.numeroTelefonico = numeroTelefonico;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String numeroTelefonico() {
                return numeroTelefonico;
            }
            @Override
            public String correoElectronico() {
                return correoElectronico;
            }
        };
    }

    interface Props{
        String numeroTelefonico();
        String correoElectronico();
    }
}
