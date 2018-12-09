package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudCerrada {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Administrador administrador;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    private EstadoDeSolicitud estado;

    @ManyToOne
    private DispositivoDeCatalogo dispositivo;

    protected SolicitudCerrada() {
    }

    public SolicitudCerrada(Cliente cliente, Administrador administrador, LocalDateTime fechaCreacion, DispositivoDeCatalogo dispositivo, EstadoDeSolicitud estado) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = LocalDateTime.now();
        this.estado = estado;
        this.dispositivo = dispositivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public EstadoDeSolicitud getEstado() {
        return estado;
    }

    public DispositivoDeCatalogo getDispositivo() {
        return dispositivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
