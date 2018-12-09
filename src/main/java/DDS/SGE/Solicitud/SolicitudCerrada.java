package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudCerrada extends Solicitud {
    @ManyToOne
    private Administrador administrador;

    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    private EstadoDeSolicitud estado;

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

    public Administrador getAdministrador() {
        return administrador;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public EstadoDeSolicitud getEstado() {
        return estado;
    }

    public String getFechaCierreCheta() {
        return this.fechaCheta(fechaCierre);
    }
}
