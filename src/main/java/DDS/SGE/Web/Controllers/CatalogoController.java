package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Estado.TablaDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class CatalogoController extends Controller {
    public static ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();

        TablaDispositivos td = new TablaDispositivos();
        viewModel.put("dispositivos", td.getDispositivos());

        td.getDispositivos().forEach(d -> System.out.println(d.getNombre()));

        return new ModelAndView(viewModel, "catalogo.hbs");
    }

    public static ModelAndView adquirir(Request request, Response response) {
        return null;
    }
}
