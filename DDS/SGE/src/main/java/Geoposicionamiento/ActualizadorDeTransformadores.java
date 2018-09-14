package Geoposicionamiento;

import java.util.List;

public class ActualizadorDeTransformadores {

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

}
