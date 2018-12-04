package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

@Entity
public class Dispositivo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private double tiempoQueSePuedeUtilizar;

    private boolean esDeCatalogo;
    private boolean bajoConsumo;

    @OneToOne(cascade = CascadeType.ALL)
    private TipoDispositivo tipo;

    protected Dispositivo() {
    }

    public Dispositivo(String nombre, TipoDispositivo tipo, boolean bajoConsumo, boolean esDeCatalogo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.bajoConsumo = bajoConsumo;
        this.esDeCatalogo = esDeCatalogo;
    }

    public Dispositivo(String nombre, TipoDispositivo tipo, boolean bajoConsumo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.bajoConsumo = bajoConsumo;
    }

    public Dispositivo(String nombre, TipoDispositivo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.esDeCatalogo = false;
    }

    public Dispositivo(TipoDispositivo tipo) {
        this.tipo = tipo;
        this.esDeCatalogo = false;
        this.nombre = tipo.toString();
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

    public double getUsoMensualMinimo() {
        return tipo.usoMensualMinimo();
    }

    public double getUsoMensualMaximo() {
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

    public void setEsDeCatalogo(boolean esDeCatalogo) {
        this.esDeCatalogo = esDeCatalogo;
    }

    public String getInteligente() {
        if (this.tipo instanceof DispositivoInteligente)
            return "sí";
        else
            return "no";
    }

    public double getConsumokWh() {
        return this.obtenerConsumoKWPorHora();
    }

    public String getBajoConsumo() {
        if (this.bajoConsumo)
            return "sí";
        else
            return "no";
    }
}
