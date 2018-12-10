package DDS.SGE.Usuarie;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrador implements Usuario {

    private String username;
    private String password;

    private String nombre;
    private String apellido;
    private String domicilio;
    private LocalDateTime fechaAltaSistema;

    private boolean tieneNotificaciones;

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

    public Administrador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Administrador(String nombre, String apellido, String domicilio, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.username = username;
        this.password = password;
        this.fechaAltaSistema = LocalDateTime.now();
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
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getTieneNotificaciones() {
        return tieneNotificaciones;
    }

    public void setTieneNotificaciones(boolean tieneNotificaciones) {
        this.tieneNotificaciones = tieneNotificaciones;
    }
}
