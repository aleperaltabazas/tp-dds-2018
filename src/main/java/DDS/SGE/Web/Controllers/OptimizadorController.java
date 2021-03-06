package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Optimizador.Optimizador;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

import static DDS.SGE.Web.Controllers.Routes.*;

public class OptimizadorController extends Controller {

    public ModelAndView mostrar(Request req, Response res) {
        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        Optimizador optimizador = new Optimizador();
        double[] resultado = optimizador.tiempoRecomendadoPorDispositivo(cliente);
        //Arrays.asList(resultado);
        List<Double> resultadoEnLista = generarLista(resultado);
        List<String> resultadoEnString = new ArrayList<String>();
        for (int i = 0; i < resultado.length; i++) {
            if (resultadoEnLista.get(i) != 0) resultadoEnString.add(String.valueOf(resultadoEnLista.get(i)));
            else resultadoEnString.add("No se encuentran mediciones para este dispositivo");
        }
        double usoMensual = optimizador.usoMensualRecomendado(cliente);
        String stringUsoMensual;
        if (usoMensual != 0) stringUsoMensual = String.valueOf(usoMensual);
        else stringUsoMensual = "No se encuentran mediciones aquí.";

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("resultado", resultadoEnString);
        viewModel.put("mensual", stringUsoMensual);

        return new ModelAndView(viewModel, "optimizer.hbs");
    }

    private List<Double> generarLista(double[] resultado) {
        List<Double> lista = new ArrayList<>();

        for (double aResultado : resultado) {
            lista.add(new Double(aResultado));
        }

        return lista;
    }
}
