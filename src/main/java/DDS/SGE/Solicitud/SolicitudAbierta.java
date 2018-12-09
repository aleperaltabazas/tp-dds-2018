package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudAbierta extends Solicitud {
    @Id
    @GeneratedValue
    private Long id;

    protected SolicitudAbierta() {
    }

    public SolicitudAbierta(Cliente cliente, DispositivoDeCatalogo dispositivo) {
        this.cliente = cliente;
        this.dispositivo = dispositivo;
        this.fechaCreacion = LocalDateTime.now();
    }

    private SolicitudCerrada cerrar(Administrador administrador, Resolucion resolucion) {
        return new SolicitudCerrada(this.cliente, administrador, this.fechaCreacion, this.dispositivo, resolucion, this.id);
    }

    public SolicitudCerrada aceptar(Administrador administrador) {
        cliente.agregarDispositivo(dispositivo.construir());
        return this.cerrar(administrador, Resolucion.aceptada);
    }

    public SolicitudCerrada rechazar(Administrador administrador) {
        return this.cerrar(administrador, Resolucion.rechazada);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}