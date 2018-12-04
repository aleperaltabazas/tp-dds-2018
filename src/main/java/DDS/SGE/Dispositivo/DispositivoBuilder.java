package DDS.SGE.Dispositivo;

import DDS.SGE.Dispositivo.Estado.Apagado;
import Fabricante.*;

public class DispositivoBuilder {
    public Dispositivo construirInteligente(String nombre, double consumo, Fabricante fabricante, boolean bajoConsumo) {
        if (nombre == null || fabricante == null) {
            throw new RuntimeException("Faltan parámetros para poder crear");
        }

        return new Dispositivo(nombre, new DispositivoInteligente(new Apagado(), fabricante), bajoConsumo);
    }
}
