package DDS.SGE;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrador {
	private String username;
	private String password;

	private String nombre;
	private String apellido;
	private String domicilio;
	private LocalDateTime fechaAltaSistema;

	@Id
	@GeneratedValue
	private Long id;

	protected Administrador() {

	}

	public Administrador(String nombre, String apellido, String domicilio, LocalDateTime fechaAltaSistema) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.fechaAltaSistema = fechaAltaSistema;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public Long getId() {
		return this.id;
	}

	public LocalDateTime getFechaAltaSistema() {
		return this.fechaAltaSistema;
	}

	public void setFechaAltaSistema(int anio, int mes, int dia) {
		this.fechaAltaSistema = this.fechaAltaSistema.withDayOfMonth(dia).withMonth(mes).withYear(anio);
	}

	public long cantidadDeMesesComoAdmin() {
		LocalDateTime localDate = LocalDateTime.now();
		return ChronoUnit.MONTHS.between(this.fechaAltaSistema, localDate);
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}
}
