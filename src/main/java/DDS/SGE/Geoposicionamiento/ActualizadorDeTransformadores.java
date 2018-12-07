package DDS.SGE.Geoposicionamiento;

import java.util.List;

import javax.persistence.*;

@Entity
public class ActualizadorDeTransformadores {

	@Id
	@GeneratedValue
	private Long id;

	@Transient
	private List<Transformador> transformadoresActivos;

	public void RecibirMensajeDelENRE(List<Transformador> transformadores) {
		this.transformadoresActivos.clear();
		this.transformadoresActivos.addAll(transformadores);
	}

	public void ActualizarTransformadores(List<Transformador> transformadoresMesPasado) {
	}

	public List<Transformador> getTransformadoresActivos() {
		return this.transformadoresActivos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
