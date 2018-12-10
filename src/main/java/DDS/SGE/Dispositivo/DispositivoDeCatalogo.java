package DDS.SGE.Dispositivo;

import javax.persistence.*;

@Entity
public class DispositivoDeCatalogo {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String detalle = "TODO";
    private double consumo;
    private boolean bajoConsumo;
    private boolean inteligente;
    private String marca = "TODO";

    @Enumerated(EnumType.STRING)
    private MetodoDeCreacion metodoDeCreacion;

    protected DispositivoDeCatalogo() {
    }

    public DispositivoDeCatalogo(String nombre, double consumo, boolean bajoConsumo, boolean inteligente, MetodoDeCreacion metodoDeCreacion) {
        this.nombre = nombre;
        this.consumo = consumo;
        this.bajoConsumo = bajoConsumo;
        this.inteligente = inteligente;
        this.metodoDeCreacion = metodoDeCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dispositivo construir() {
        metodoDeCreacion.fill(nombre, consumo, bajoConsumo, inteligente);
        return metodoDeCreacion.construir();
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNombreCorto() {
        if (nombre.length() > 20) {
            return nombre.substring(0, 17) + "...";
        } else {
            return nombre;
        }
    }

    public double getConsumo() {
        return this.consumo;
    }

    public String getBajoConsumo() {
        return bajoConsumo ? "sí" : "no";
    }

    public String getInteligente() {
        return inteligente ? "sí" : "no";
    }

    public String getDetalle() {
        return detalle;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return metodoDeCreacion.toString();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
