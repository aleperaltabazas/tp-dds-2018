package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudCerrada extends Solicitud {
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Administrador administrador;

    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    private Resolucion estado;

    protected SolicitudCerrada() {
    }

    public SolicitudCerrada(Cliente cliente, Administrador administrador, LocalDateTime fechaCreacion, DispositivoDeCatalogo dispositivo, Resolucion estado, Long id) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = LocalDateTime.now();
        this.estado = estado;
        this.dispositivo = dispositivo;
        this.id = id;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public Resolucion getEstado() {
        return estado;
    }

    public String getFechaCierreCheta() {
        return this.fechaCheta(fechaCierre);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
