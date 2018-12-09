package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudAbierta {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    private LocalDateTime fechaCreacion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private DispositivoDeCatalogo dispositivo;

    protected SolicitudAbierta() {
    }

    public SolicitudAbierta(Cliente cliente, DispositivoDeCatalogo dispositivo) {
        this.cliente = cliente;
        this.dispositivo = dispositivo;
        this.fechaCreacion = LocalDateTime.now();
    }

    public SolicitudCerrada aceptar(Administrador administrador) {
        cliente.agregarDispositivo(dispositivo.construir());
        return new SolicitudCerrada(this.cliente, administrador, this.fechaCreacion, this.dispositivo, EstadoDeSolicitud.ACEPTADA);
    }

    public SolicitudCerrada rechazar(Administrador administrador) {
        return new SolicitudCerrada(this.cliente, administrador, this.fechaCreacion, this.dispositivo, EstadoDeSolicitud.RECHAZADA);
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    public DispositivoDeCatalogo getDispositivo() {
        return this.dispositivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}