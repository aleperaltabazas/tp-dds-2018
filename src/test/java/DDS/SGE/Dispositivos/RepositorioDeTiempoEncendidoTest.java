package DDS.SGE.Dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.RepositorioDeTiempoEncendido;

public class RepositorioDeTiempoEncendidoTest extends RepositorioDeTiempoEncendido {
    public RepositorioDeTiempoEncendidoTest(List<IntervaloActivo> intervalosDeActividad) {
        this.setIntervalosDeActividad(intervalosDeActividad);
        this.setUltimaFechaDeEncendido(LocalDateTime.now());
    }
}
