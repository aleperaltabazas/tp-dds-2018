package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.*;

public class RepositorioDeTiempoEncendido {
	
	private LocalDateTime ultimaFechaDeEncendido;
	private ArrayList<IntervaloActivo> intervalosDeActividad = new ArrayList<IntervaloActivo>();
	
	public RepositorioDeTiempoEncendido(ArrayList<IntervaloActivo> intervalos) {
		this.intervalosDeActividad = intervalos;
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
