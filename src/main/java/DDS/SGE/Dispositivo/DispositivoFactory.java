package DDS.SGE.Dispositivo;

import DDS.SGE.Dispositivo.Estado.*;
import Fabricante.*;

public class DispositivoFactory {
    public DispositivoInteligente crearAire(int frigorias, double consumo) {
        return new DispositivoInteligente(new Apagado(), new AireAcondicionado(frigorias, consumo));
    }

    public DispositivoEstandar crearTVBoba(double consumo) {
        return new DispositivoEstandar(0, consumo);
    }

    public DispositivoInteligente crearLED(int pulgadas, double consumo) {
        return new DispositivoInteligente(new Apagado(), new Televisor(pulgadas, Televisor.TipoTelevisor.LED, consumo));
    }

    public DispositivoInteligente crearHeladera(double consumo, boolean freezer) {
        return new DispositivoInteligente(new Apagado(), new Heladera(consumo, freezer));
    }

    public DispositivoEstandar crearLavarropasBobo(double consumo) {
        return new DispositivoEstandar(0, consumo);
    }

    public DispositivoInteligente crearLavarropsInteligente() {
        return new DispositivoInteligente(new Apagado(), new Lavarropas(5, true, false));
    }

    public DispositivoEstandar crearHalogena(int watts) {
        return new DispositivoEstandar(0, watts / 100);
    }

    public DispositivoInteligente crearLampara(int watts) {
        return new DispositivoInteligente(new Apagado(), new Lampara(watts, false));
    }

    public DispositivoInteligente crearPCDesktop() {
        return new DispositivoInteligente(new Apagado(), new Computadora(false));
    }

    public DispositivoEstandar crearPlancha() {
        return new DispositivoEstandar(0, 0.75);
    }

}
