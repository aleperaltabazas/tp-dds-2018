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

    public void aceptar(Administrador administrador) {
        new ChabonQueTePersisteCuandoSeCierraUnaSolicitud().aceptar(this, administrador);
    }

    public void rechazar(Administrador administrador) {
        new ChabonQueTePersisteCuandoSeCierraUnaSolicitud().rechazar(this, administrador);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}