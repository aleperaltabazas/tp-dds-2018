package DDS.SGE.Usuarie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DDS.SGE.Usuarie.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Web.HashProvider;

public class ClienteBuilder {
    private TipoDni tipoDocumento = TipoDni.DNI;
    private String direccionDefault = "Avenida Medrano 951";
    private List<Dispositivo> dispositivosDefault = new ArrayList<>();

    public void especificarTipoDocumento(String tipo) {
        if (tipo == "CI")
            tipoDocumento = TipoDni.CI;
        else if (tipo == "LE")
            tipoDocumento = TipoDni.LE;
        else if (tipo == "LC")
            tipoDocumento = TipoDni.LC;
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
        return new Cliente(nombre, apellido, tipoDocumento, numeroDni, telefono, direccionDefault, LocalDateTime.now(),
                dispositivosDefault, username, HashProvider.hash(password).toLowerCase());
    }

    public Cliente crearCliente(String nombre, String apellido, TipoDni tipo, String numeroDni, String telefono,
                                String direccion, LocalDateTime fecha, List<Dispositivo> dispositivos, String username, String password) {

        return new Cliente(nombre, apellido, tipo, numeroDni, telefono, direccion, LocalDateTime.now(), dispositivos,
                username, HashProvider.hash(password).toLowerCase());
    }

    public Cliente crearCliente(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("Complete todos los campos");
        }

        return new Cliente(username, HashProvider.hash(password).toLowerCase());
    }

}
