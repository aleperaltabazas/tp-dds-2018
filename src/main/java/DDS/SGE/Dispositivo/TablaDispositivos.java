package DDS.SGE.Dispositivo;

import java.util.Arrays;
import java.util.List;

public class TablaDispositivos {
    private List<Dispositivo> dispositivos;

    public TablaDispositivos() {
        DispositivoFactory df = new DispositivoFactory();

        Dispositivo aire3500 = new Dispositivo("Aire acondicionado - 3500 frigorías", df.crearAire(3500, 1.613), false, true);
        Dispositivo aire2200 = new Dispositivo("Aire acondicionado - 2200 frigorías", df.crearAire(2200, 1.013), true, true);

        Dispositivo ctr21 = new Dispositivo("Televisor - tubo fluorescente 21\"", df.crearTVBoba(0.075), false, true);
        Dispositivo ctr29 = new Dispositivo("Televisor - tubo fluorescente 29\"", df.crearTVBoba(0.175), false, true);
        Dispositivo ctr34 = new Dispositivo("Televisor - tubo fluorescente 34\"", df.crearTVBoba(0.175), false, true);
        Dispositivo lcd40 = new Dispositivo("LCD - 40\"", df.crearTVBoba(0.18), false, true);
        Dispositivo led24 = new Dispositivo("LED - 34\"", df.crearLED(24, 0.04), true, true);
        Dispositivo led32 = new Dispositivo("LED - 32\"", df.crearLED(32, 0.055), true, true);
        Dispositivo led40 = new Dispositivo("LED - 40\"", df.crearLED(40, 0.08), true, true);

        Dispositivo heladeraConFreezer = new Dispositivo("Heladera - con freezer", df.crearHeladera(0.09, true), true, true);
        Dispositivo heladeraSinFreezer = new Dispositivo("Heladera - sin freezer", df.crearHeladera(0.075, false), true, true);

        Dispositivo lavarropasAutoCalentamiento = new Dispositivo("Lavarropas automático - con calentamiento de agua", df.crearLavarropasBobo(0.875), false, true);
        Dispositivo lavarropasAuto = new Dispositivo("Lavarropas automático - 5kg", df.crearLavarropasBobo(0.175), true, true);
        Dispositivo lavarropasSemi = new Dispositivo("Lavarropas semiautomático - 5kg", df.crearLavarropasInteligente(), true, true);

        this.dispositivos = Arrays.asList(aire3500, aire2200, ctr21, ctr29, ctr34, lcd40, led24, led32, led40, heladeraConFreezer, heladeraSinFreezer, lavarropasAutoCalentamiento, lavarropasAuto, lavarropasSemi);
    }

    public List<Dispositivo> getDispositivos() {
        return this.dispositivos;
    }
}
