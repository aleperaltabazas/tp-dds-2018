package DDS.SGE.Dispositivo;

import java.util.Arrays;
import java.util.List;

public class TablaDispositivos {
    private List<DispositivoDeCatalogo> dispositivos;

    public TablaDispositivos() {
        DispositivoFactory df = new DispositivoFactory();

        DispositivoDeCatalogo aire3500 = new DispositivoDeCatalogo("Aire acondicionado", 1.613, false, true, MetodoDeCreacion.AIRE);
        DispositivoDeCatalogo aire2200 = new DispositivoDeCatalogo("Aire acondicionado", 1.013, true, true, MetodoDeCreacion.AIRE);

        DispositivoDeCatalogo ctr21 = new DispositivoDeCatalogo("Televisor - tubo fluorescente 21\"", 0.075, false, true, MetodoDeCreacion.TELE);
        DispositivoDeCatalogo ctr29 = new DispositivoDeCatalogo("Televisor - tubo fluorescente 29\"", 0.175, false, true, MetodoDeCreacion.TELE);

        this.dispositivos = Arrays.asList(aire3500, aire2200, ctr21, ctr29);
    }

    public List<DispositivoDeCatalogo> getDispositivos() {
        return this.dispositivos;
    }
}
