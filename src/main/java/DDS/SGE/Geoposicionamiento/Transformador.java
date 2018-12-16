package DDS.SGE.Geoposicionamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import DDS.SGE.Usuarie.Cliente;

@Entity
public class Transformador {

    @Id
    @GeneratedValue
    private Long id;

    private int codigo;

    @OneToMany()
    @JoinColumn(name = "id")
    @Transient
    List<Cliente> usuarios = new ArrayList<Cliente>();

    double energia;
    boolean activo;

    protected Transformador() {
    }

    public Transformador(int i) {
        codigo = i;
        activo = true;
    }

    public Long getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Cliente> getUsuarios() {
        return usuarios;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void agregarCliente(Cliente nuevoUsuario) {
        this.usuarios.add(nuevoUsuario);
    }

    public double suministra() {
        return usuarios.stream().mapToDouble(cliente -> cliente.consumoTotalEstimadoDiario()).sum();
    }

    /*
     * public boolean perteneceA(Zona unaZona) { return unaZona == this.getZona(); }
     */

    public double getEnergia() {
        return this.energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double consumoEnElPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return usuarios.stream().mapToDouble(c -> c.consumoTotalEnUnPeriodo(fechaInicio, fechaFin)).sum();
    }

}
