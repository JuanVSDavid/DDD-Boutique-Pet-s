package co.com.boutiquepet.ddd.gestordepedidos.domain.values;

import co.com.boutiquepet.ddd.gestordepedidos.business.generic.ValueObject;

import java.util.Objects;

public class Medidas implements ValueObject<Medidas.Props> {
    private double contornoCuelloCM;
    private double contornoPechoCM;
    private double largoLomoCM;
    private double largoMargaCM;

    public Medidas(double contornoCuelloCM, double contornoPechoCM, double largoLomoCM, double largoMargaCM) {
        this.contornoCuelloCM = Objects.requireNonNull(contornoCuelloCM);
        this.contornoPechoCM = Objects.requireNonNull(contornoPechoCM);
        this.largoLomoCM = Objects.requireNonNull(largoLomoCM);
        this.largoMargaCM = largoMargaCM;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public double contornoCuelloCM() {
                return contornoCuelloCM;
            }

            @Override
            public double contornoPechoCM() {
                return contornoPechoCM;
            }

            @Override
            public double largoLomoCM() {
                return largoLomoCM;
            }

            @Override
            public double largoMangaCM() {
                return largoMargaCM;
            }
        };
    }

    interface Props{
        double contornoCuelloCM();
        double contornoPechoCM();
        double largoLomoCM();
        double largoMangaCM();
    }
}
