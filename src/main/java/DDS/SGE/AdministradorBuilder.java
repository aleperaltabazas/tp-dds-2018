package DDS.SGE;

import DDS.SGE.Web.HashProvider;

public class AdministradorBuilder {
    private String direccion = "Av. Medrano 951";

    public void especificarDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Administrador admin(String nombre, String apellido, String username, String password) {
        if (nombre == null || apellido == null || username == null || password == null) {
            throw new RuntimeException("Need more parameters to build");
        }

        return new Administrador(nombre, apellido, direccion, username, HashProvider.hash(password));
    }
}
