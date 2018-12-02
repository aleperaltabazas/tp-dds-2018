package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;
import DDS.SGE.Optimizador;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

import static DDS.SGE.Web.Controllers.Routes.*;

public class OptimizadorController extends Controller {

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

        Optimizador optimizador = new Optimizador();
        double[] resultado = optimizador.tiempoRecomendadoPorDispositivo(cliente);

        List<Double> resultadoEnLista = generarLista(resultado);

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("resultado", resultadoEnLista);

        return new ModelAndView(viewModel, "optimizer.hbs");
    }

    private static List<Double> generarLista(double[] resultado) {
        List<Double> lista = new ArrayList<>();

        for (int i = 0; i < resultado.length; i++) {
            lista.add(new Double(resultado[i]));
        }

        return lista;
    }
}
