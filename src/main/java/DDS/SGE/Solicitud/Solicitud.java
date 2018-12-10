package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Usuarie.Cliente;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public abstract class Solicitud {
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Cliente cliente;

    @ManyToOne(cascade = CascadeType.PERSIST)
    protected DispositivoDeCatalogo dispositivo;

    protected LocalDateTime fechaCreacion;

    public String getIdCheto() {
        DecimalFormat df = new DecimalFormat("0000000");
        return df.format(this.getId());
    }

    protected String fechaCheta(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    public String getFechaCreacionCheta() {
        return this.fechaCheta(fechaCreacion);
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

    public abstract Long getId();

    public abstract void setId(Long id);
}
