package DDS.SGE.Dispositivo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DDS.SGE.Dispositivo.Estado.*;
import Fabricante.*;
import Fabricante.Televisor.TipoTelevisor;

public class TablaDeDispositivos {
	
	Dispositivo aireAcondicionadoPoderoso = new Dispositivo(1.613,new DispositivoInteligente(new Apagado(), new AireAcondicionado(3500)));
	Dispositivo aireAcondicionadoDebil = new Dispositivo(1.013,new DispositivoInteligente(new Apagado(), new AireAcondicionado(2500)));
	Dispositivo televisorTuboChico = new Dispositivo(0.075,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande29 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande30 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande31 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande32 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande33 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorTuboGrande34 = new Dispositivo(0.175,new DispositivoEstandar(2));
	Dispositivo televisorLCD = new Dispositivo(0.18,new DispositivoEstandar(2));
	Dispositivo televisorTuboLEDChico = new Dispositivo(0.04,new DispositivoInteligente(new Apagado(), new Televisor(24,TipoTelevisor.LED)));
	Dispositivo televisorTuboLEDMediano = new Dispositivo(0.055,new DispositivoInteligente(new Apagado(), new Televisor(32,TipoTelevisor.LED)));
	Dispositivo televisorTuboLEDGrande = new Dispositivo(0.08,new DispositivoInteligente(new Apagado(), new Televisor(40,TipoTelevisor.LED)));
	Dispositivo heladeraConFreezer = new Dispositivo(0.09,new DispositivoInteligente(new Apagado(), new Heladera(true)));
	Dispositivo heladeraSinFreezer = new Dispositivo(0.075,new DispositivoInteligente(new Apagado(), new Heladera(false)));
	Dispositivo lavarropasAutomaticoConCalentamiento = new Dispositivo(0.875,new DispositivoEstandar(2));
	Dispositivo lavarropasAutomaticoSinCalentamiento = new Dispositivo(0.175,new DispositivoInteligente(new Apagado(), new Lavarropas(5,true,false)));
	Dispositivo lavarropasSemiAutomatico = new Dispositivo(0.1275,new DispositivoEstandar(2));
	Dispositivo ventiladorDePie = new Dispositivo(0.09,new DispositivoEstandar(2));
	Dispositivo ventiladorDeTecho = new Dispositivo(0.06,new DispositivoInteligente(new Apagado(), new Ventilador(false)));
	Dispositivo lamparaHalogenasPequenia = new Dispositivo(0.04,new DispositivoInteligente(new Apagado(), new Lampara(40,true)));
	Dispositivo lamparaHalogenasMediana = new Dispositivo(0.06,new DispositivoInteligente(new Apagado(), new Lampara(60,true)));
	Dispositivo lamparaHalogenasGrande = new Dispositivo(0.015,new DispositivoInteligente(new Apagado(), new Lampara(100,true)));
	Dispositivo lamparaPequenia = new Dispositivo(0.011,new DispositivoInteligente(new Apagado(), new Lampara(11,false)));
	Dispositivo lamparaMediana = new Dispositivo(0.015,new DispositivoInteligente(new Apagado(), new Lampara(15,false)));
	Dispositivo lamparaGrande = new Dispositivo(0.02,new DispositivoInteligente(new Apagado(), new Lampara(20,false)));
	Dispositivo pc = new Dispositivo(0.4,new DispositivoInteligente(new Apagado(), new Computadora(false)));
	Dispositivo microondas = new Dispositivo(0.64,new DispositivoEstandar(2));

	Map<String, Dispositivo> map = new HashMap<String, Dispositivo>();
	
	public Dispositivo obtenerDispositivo(String dispositivo) {
		map.put("aireAcondicionadoPoderoso", aireAcondicionadoPoderoso);
		map.put("aireAcondicionadoDebil", aireAcondicionadoDebil);
		map.put("televisorTuboChico", televisorTuboChico);
		map.put("televisorTuboGrande29", televisorTuboGrande29);
		map.put("televisorTuboGrande30", televisorTuboGrande30);
		map.put("televisorTuboGrande31", televisorTuboGrande31);
		map.put("televisorTuboGrande32", televisorTuboGrande32);
		map.put("televisorTuboGrande33", televisorTuboGrande33);
		map.put("televisorTuboGrande34", televisorTuboGrande34);
		map.put("televisorLCD", televisorLCD);
		map.put("televisorTuboLEDChico", televisorTuboLEDChico);
		map.put("televisorTuboLEDMediano", televisorTuboLEDMediano);
		map.put("televisorTuboChico", televisorTuboChico);
		map.put("televisorTuboLEDGrande", televisorTuboLEDGrande);
		map.put("heladeraConFreezer", heladeraConFreezer);
		map.put("heladeraSinFreezer", heladeraSinFreezer);
		map.put("lavarropasAutomaticoConCalentamiento", lavarropasAutomaticoConCalentamiento);
		map.put("lavarropasAutomaticoSinCalentamiento", lavarropasAutomaticoSinCalentamiento);
		map.put("lavarropasSemiAutomatico", lavarropasSemiAutomatico);
		map.put("ventiladorDePie", ventiladorDePie);
		map.put("ventiladorDeTecho", ventiladorDeTecho);
		map.put("lamparaHalogenasPequenia", lamparaHalogenasPequenia);
		map.put("lamparaHalogenasMediana", lamparaHalogenasMediana);
		map.put("lamparaHalogenasGrande", lamparaHalogenasGrande);
		map.put("lamparaPequenia", lamparaPequenia);
		map.put("lamparaMediana", lamparaMediana);
		map.put("lamparaGrande", lamparaGrande);
		map.put("pc", pc);
		map.put("microondas", microondas);
		
		return map.get(dispositivo);
	}
}