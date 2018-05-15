package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public interface EstadoDelDispositivo {
	boolean estaEncendido();

	double getIntensidad();

	void setIntensidad(double nuevoValor);

	void setModo(ModoFrio_Calor nuevoModo);

	void apagar(DispositivoInteligente dispositivo);

	void encender(DispositivoInteligente dispositivo);

	void ahorraEnergia(DispositivoInteligente dispositivo);
}
