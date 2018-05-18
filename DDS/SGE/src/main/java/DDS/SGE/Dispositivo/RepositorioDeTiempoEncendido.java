package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class RepositorioDeTiempoEncendido {
	
	public LocalDateTime ultimaFechaDeEncendido;
	public List<IntervaloActivo> intervalosDeActividad;
	
	public RepositorioDeTiempoEncendido() {
		ultimaFechaDeEncendido = LocalDateTime.now();
		intervalosDeActividad = Arrays.asList();
	}
	
	public Stream<IntervaloActivo> getIntervalosDeActividad(){
		return intervalosDeActividad.stream();
	}

	//Hay que evitar que si esta encendido se encienda y si esta apagado se apague porque sino se generan conflictos
	public void encender(LocalDateTime fechaEncendido) {
		ultimaFechaDeEncendido = fechaEncendido;
	}

	public void apagar(LocalDateTime fechaApagado) {
		intervalosDeActividad.add(new IntervaloActivo(ultimaFechaDeEncendido, fechaApagado));
	}
	
	public long tiempoTotalEncendidoHaceNHorasEnMinutos(int horas) {		
		LocalDateTime fechaSolicitada = LocalDateTime.now().minusHours(horas);
		return this.intervalosQueOcurrenEntre(fechaSolicitada, LocalDateTime.now()).mapToLong(intervalo -> intervalo.getIntervaloEncendidoEnMinutos()).sum();
	}
	
	public Stream<IntervaloActivo> intervalosQueOcurrenEntre(LocalDateTime tiempoMinimo, LocalDateTime tiempoMaximo){
		return this.getIntervalosDeActividad().filter(intervalo -> intervalo.ocurreDespuesDe(tiempoMinimo) && intervalo.ocurreAntesDe(tiempoMaximo));
	}
		
	public double tiempoTotalEnUnPeriodoEnMinutos(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return this.intervalosQueOcurrenEntre(principioPeriodo, finPeriodo).mapToLong(intervalo -> intervalo.getIntervaloEncendidoEnMinutos()).sum();
	}

}
