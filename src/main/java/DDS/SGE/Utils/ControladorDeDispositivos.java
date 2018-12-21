package DDS.SGE.Utils;

import DDS.SGE.Optimizador.Optimizador;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ControladorDeDispositivos implements TransactionalOps, WithGlobalEntityManager {
    private static final Logger LOGGER = Logger.getLogger(ControladorDeDispositivos.class.getName());

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask tarea = new TimerTask() {

            @Override
            public void run() {
                LOGGER.log(INFO, "Controlando dispositivos...");

                try {
                    new ControladorDeDispositivos().optimizar();
                    LOGGER.log(INFO, "Dispositivos controlados.");
                    LOGGER.log(INFO, "Revisando de nuevo en un minuto.");
                } catch (Exception e) {
                    LOGGER.log(INFO, e.getMessage());
                }
            }
        };

        timer.schedule(tarea, 0, 60000);
    }

    private void optimizar() {
        Optimizador optimizador = new Optimizador();

        List<Cliente> clientes = RepositorioClientes.getInstance().getAllClients();
        clientes.forEach(c -> {
            optimizador.simplex(c);
            if (c.getPermiteApagar()) {
                LOGGER.log(INFO, "Dispositivos de " + c.getNombre() + " controlados");
                optimizador.accionarSobreDispositivos(
                        optimizador.obtenerDispositivosInfractores(c.getDispositivos()));
            }
        });

        withTransaction(() -> clientes.forEach(c -> {
            RepositorioClientes.getInstance().saveOrUpdate(c);
        }));
    }

}
