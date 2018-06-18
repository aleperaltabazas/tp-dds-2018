package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import org.json.JSONObject;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

public class Dispositivo {
	private String nombre;
	private double consumoKWPorHora;
	private TipoDispositivo tipo;
	Fabricante fabricante;

	public Dispositivo(double consumoKWPorHora, TipoDispositivo tipo, Fabricante fabricante) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.tipo = tipo;
		this.fabricante = fabricante;
	}
	
	public TipoDispositivo getTipoDispositivo() {
		return this.tipo;
	}
	
	public void setTipoDispositvo(TipoDispositivo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
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
	
	//Implementar, por lo que entendi del encunciado mediante esto el dispositivo se apaga o prende
	/*public void recibirActuador(Actuador unActuador) {
		//Opcion para que no haga nada con los estandar seria lanzar una excepcion
		//Generar efecto sobre el DI
	}*/

}
