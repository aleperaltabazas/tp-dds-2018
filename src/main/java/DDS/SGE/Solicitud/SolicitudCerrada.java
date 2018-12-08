package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.*;

import java.time.LocalDateTime;

public class SolicitudCerrada {
    private Cliente cliente;
    private Administrador administrador;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;
    private EstadoDeSolicitud estado;
    private DispositivoDeCatalogo dispositivo;

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
}
