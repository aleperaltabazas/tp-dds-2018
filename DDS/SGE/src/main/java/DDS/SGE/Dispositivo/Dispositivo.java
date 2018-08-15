package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import org.json.JSONObject;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

public class Dispositivo {

	private double consumoKWPorHora;
	private double tiempoQueSePuedeUtilizar;
	private TipoDispositivo tipo;

	public Dispositivo(double consumoKWPorHora, TipoDispositivo tipo) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.tipo = tipo;
	}
	
	public TipoDispositivo getTipoDispositivo() {
		return this.tipo;
	}
	
	public double getTiempoQueSePuedeUtilizar() {
		return this.tiempoQueSePuedeUtilizar;
	}
	
	public void setTiempoQueSePuedeUtilizar(double tiempoQueSePuedeUtilizar) {
		this.tiempoQueSePuedeUtilizar = tiempoQueSePuedeUtilizar;
	}
	
	public void setTipoDispositvo(TipoDispositivo tipo) {
		this.tipo = tipo;
	}

	public boolean estaEncendido() {
		return tipo.estaEncendido();
	}

	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	public double consumoDiarioEstimado() {
		return consumoKWPorHora * tipo.usoEstimadoDiario();
	}
	
	public void adaptarConModulo() {
		this.tipo = tipo.adaptar();
	}
	
	public void encender() {
		this.tipo.encender();
	}
	
	public void apagar() {
		this.tipo.apagar();
	}

	public double consumoTotalHaceNHoras(int horas) {
		return tipo.tiempoTotalEncendidoHaceNHoras(horas) * consumoKWPorHora;
	}
	
	public double consumoTotalEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return tipo.tiempoTotalEncendidoEnUnPeriodo(principioPeriodo, finPeriodo) * consumoKWPorHora;

	}

	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		this.tipo.seAgregoNuevoDispositivo(interesadoEnNuevosDispositivos);
		
	}

	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		this.tipo.seAdaptoUnDispositivo(interesadoEnAdaptaciones);
		
	}
	
	public double usoMensualMinimo() {
		return tipo.usoMensualMinimo();
	}
	
	public double usoMensualMaximo() {
		return tipo.usoMensualMaximo();
	}
}
