package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Dispositivo> dispositivos = RepositorioDispositivos.dispositivosDe(cliente);
        List<Dispositivo> dispositivosInteligentes = dispositivos.stream().filter(d -> d.getTipoDispositivo() instanceof DispositivoInteligente).collect(Collectors.toList());
        List<Dispositivo> dispositivosEstandar = dispositivos.stream().filter(d -> d.getTipoDispositivo() instanceof DispositivoEstandar).collect(Collectors.toList());

        viewModel.put("dispositivosInteligente", dispositivosInteligentes);
        viewModel.put("dispositivosEstandar", dispositivosEstandar);

        //TODO: si al levantar el cliente levanta consigo sus dispositivos, lenvatarlos desde el repo no hace falta

        return new ModelAndView(viewModel, ruta);
    }
}
