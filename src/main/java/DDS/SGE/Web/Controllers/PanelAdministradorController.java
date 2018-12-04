package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PanelAdministradorController extends Controller {

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ModelAndView(null, "404.hbs");
        }
        //Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
        return new ModelAndView(null, "panelAdministrador.hbs");
    }

    public static ModelAndView verTodosLosHogares(Request req, Response res) {
        return new ModelAndView(null, "listado.hbs");
    }


}