package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.*;

import java.time.LocalDateTime;

public class SolicitudAbierta {
    private Cliente cliente;
    private LocalDateTime fechaCreacion;
    private DispositivoDeCatalogo dispositivo;

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
}