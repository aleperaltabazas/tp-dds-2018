package DDS.SGE.Utils;

import DDS.SGE.Optimizador.Optimizador;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ControladorDeDispositivos {
    private static final Logger LOGGER = Logger.getLogger(ControladorDeDispositivos.class.getName());

    public static void main(String[] args) {
        Optimizador optimizador = new Optimizador();
        Timer timer = new Timer();

        TimerTask tarea = new TimerTask() {

            @Override
            public void run() {
                LOGGER.log(INFO, "Controlando dispositivos...");
                List<Cliente> clientes = RepositorioClientes.getInstance().getAllClients();
                clientes.forEach(c -> {
                    optimizador.simplex(c);
                    if (c.getPermiteApagar()) {
                        optimizador.accionarSobreDispositivos(
                                optimizador.obtenerDispositivosInfractores(c.getDispositivos()));
                    }
                });

                LOGGER.log(INFO, "Dispositivos controlados.");
                LOGGER.log(INFO, "Revisando de nuevo en un minuto.");
            }
        };

        timer.schedule(tarea, 0, 60000);
    }

}
