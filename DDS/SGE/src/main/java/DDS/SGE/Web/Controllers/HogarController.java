package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class HogarController extends Controller {
    //TODO: cambiar las rutas con nombres finales
    private static String ruta = "mi-hogar-v2-posta.hbs";

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect("/");
            return HomeController.mostrar(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = rellenarCliente(cliente);
        viewModel.put("dispositivos", RepositorioDispositivos.dispositivosDe(cliente));

        return new ModelAndView(viewModel, ruta);
    }
}
