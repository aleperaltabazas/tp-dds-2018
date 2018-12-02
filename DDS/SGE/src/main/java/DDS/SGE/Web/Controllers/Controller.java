package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;

import java.util.HashMap;

public abstract class Controller {
    protected static final String SESSION_NAME = "id";

    protected static HashMap<String, Object> rellenarCliente(Cliente cliente) {
        HashMap<String, Object> viewModel = new HashMap<>();

        viewModel.put("nombre", cliente.getNombre());
        viewModel.put("apellido", cliente.getApellido());
        viewModel.put("telefono", cliente.getTelefono());
        viewModel.put("numeroDni", cliente.getNumeroDni());
        viewModel.put("tipoDni", cliente.getTipoDni());
        viewModel.put("domicilio", cliente.getDomicilio());
        viewModel.put("categoria", cliente.getCategoria().toString());
        viewModel.put("username", cliente.getUsername());

        return viewModel;
    }
}
