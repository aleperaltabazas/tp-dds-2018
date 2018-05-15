package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDeTiempoEncendido {
	private ArrayList<LocalDateTime> fechasEncendido = new ArrayList<LocalDateTime>();
	private ArrayList<LocalDateTime> fechasApagado = new ArrayList<LocalDateTime>();

	public void encender(LocalDateTime fechaEncendido) {
		fechasEncendido.add(fechaEncendido);
	}

	public void apagar(LocalDateTime fechaApagado) {
		fechasApagado.add(fechaApagado);
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		/*
		 * Si lo hacemos de una manera que tenga un poco mas de sentido tenemos que ver
		 * aca como calculamos el tiempo hace n horas y encontrar la fecha de encendido
		 * mas cercana, yendo para arriba, y a partir de eso descontar tambien el tiempo
		 * que estuvo apagado y alta paja, algo estamos pifiando porque suena re
		 * algoritmico
		 */
		return 42;
	}
}
