package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.json.JSONObject;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

@Entity
public class Dispositivo {

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private double tiempoQueSePuedeUtilizar;

	@OneToOne
	private TipoDispositivo tipo;

	public Dispositivo(TipoDispositivo tipo) {
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

	public double obtenerConsumoKWPorHora() {
		return this.tipo.getConsumoKWPorHora();
	}

	public double consumoDiarioEstimado() {
		return this.obtenerConsumoKWPorHora() * this.obtenerUsoEstimadoDiario();
	}

	private double obtenerUsoEstimadoDiario() {
		return tipo.usoEstimadoDiario();
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
		return tipo.tiempoTotalEncendidoHaceNHoras(horas) * this.obtenerConsumoKWPorHora();
	}

	public double consumoTotalEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return tipo.tiempoTotalEncendidoEnUnPeriodo(principioPeriodo, finPeriodo) * this.obtenerConsumoKWPorHora();

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
