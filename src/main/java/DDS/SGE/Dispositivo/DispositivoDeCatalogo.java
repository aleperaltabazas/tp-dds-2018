package DDS.SGE.Dispositivo;

import javax.persistence.*;

@Entity
public class DispositivoDeCatalogo {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private double consumo;
    private boolean bajoConsumo;
    private boolean inteligente;

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
}
