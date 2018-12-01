package DDS.SGE;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Web.HashProvider;

public class ClienteBuilder {
	private TipoDni documentoDefault = TipoDni.DNI;
	private String direccionDefault = "Avenida Medrano 951";
	private List<Dispositivo> dispositivosDefault = Arrays.asList();

	public void especificarTipoDocumento(TipoDni tipo) {
		this.documentoDefault = tipo;
	}

	public void especificarDireccion(String direccion) {
		this.direccionDefault = direccion;
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		dispositivosDefault.add(dispositivo);
	}

	public Cliente crearCliente(String nombre, String apellido, String numeroDni, String telefono, String username,
			String password) {
		// TODO: Chequear por null
		return new Cliente(nombre, apellido, documentoDefault, numeroDni, telefono, direccionDefault,
				LocalDateTime.now(), dispositivosDefault, username, HashProvider.hash(password));
	}

	public Cliente crearCliente(String nombre, String apellido, TipoDni tipo, String numeroDni, String telefono,
			String direccion, LocalDateTime fecha, List<Dispositivo> dispositivos, String username, String password) {

		return new Cliente(nombre, apellido, tipo, numeroDni, telefono, direccion, LocalDateTime.now(), dispositivos,
				username, HashProvider.hash(password));
	}

}
