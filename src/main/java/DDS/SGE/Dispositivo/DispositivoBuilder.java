package DDS.SGE.Dispositivo;

import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Fabricante.*;

public class DispositivoBuilder {
    public Dispositivo construirInteligente(String nombre, double consumo, Fabricante fabricante, boolean bajoConsumo) {
        if (nombre == null || fabricante == null) {
            throw new RuntimeException("Faltan parámetros para poder construir");
        }

        fabricante.setConsumoKWPorHora(consumo);
        return new Dispositivo(nombre, new DispositivoInteligente(new Apagado(), fabricante), bajoConsumo);
    }

    public Dispositivo construirEstandar(String nombre, double consumoKWPorHora, long usoEstimadoDiario, boolean bajoConsumo) {
        if (nombre == null) {
            throw new RuntimeException("Faltan parámetros para poder construir");
        }

        return new Dispositivo(nombre, new DispositivoEstandar(usoEstimadoDiario, consumoKWPorHora), bajoConsumo);
    }

    public DispositivoDeCatalogo crearDispositivoDeCatalogo(String nombre, double consumo, Fabricante fabricante, boolean bajoConsumo, boolean inteligente, MetodoDeCreacion metodoDeCreacion) {
        if (nombre == null || fabricante == null || metodoDeCreacion == null) {
            throw new RuntimeException("Faltan parámetros para poder construir");
        }

        return new DispositivoDeCatalogo(nombre, consumo, bajoConsumo, inteligente, metodoDeCreacion);
    }
}
