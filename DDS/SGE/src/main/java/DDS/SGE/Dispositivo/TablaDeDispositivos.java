package DDS.SGE.Dispositivo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.Dispositivo.Estado.*;
import Fabricante.*;
import Fabricante.Televisor.TipoTelevisor;

public class TablaDeDispositivos {
	
	Dispositivo aireAcondicionadoPoderoso = new Dispositivo(1.613,new DispositivoInteligente(new Apagado()), new AireAcondicionado(3500));
	Dispositivo aireAcondicionadoDebil = new Dispositivo(1.013,new DispositivoInteligente(new Apagado()), new AireAcondicionado(2500));
	Dispositivo televisorTuboChico = new Dispositivo(0.075,new DispositivoEstandar(2), new Televisor(21,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande29 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(29,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande30 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(30,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande31 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(31,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande32 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(32,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande33 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(33,TipoTelevisor.TUBO));
	Dispositivo televisorTuboGrande34 = new Dispositivo(0.175,new DispositivoEstandar(2), new Televisor(34,TipoTelevisor.TUBO));
	Dispositivo televisorLCD = new Dispositivo(0.18,new DispositivoEstandar(2), new Televisor(40,TipoTelevisor.LCD));
	Dispositivo televisorTuboLEDChico = new Dispositivo(0.04,new DispositivoInteligente(new Apagado()), new Televisor(24,TipoTelevisor.LED));
	Dispositivo televisorTuboLEDMediano = new Dispositivo(0.055,new DispositivoInteligente(new Apagado()), new Televisor(32,TipoTelevisor.LED));
	Dispositivo televisorTuboLEDGrande = new Dispositivo(0.08,new DispositivoInteligente(new Apagado()), new Televisor(40,TipoTelevisor.LED));
	Dispositivo heladeraConFreezer = new Dispositivo(0.09,new DispositivoInteligente(new Apagado()), new Heladera(true));
	Dispositivo heladeraSinFreezer = new Dispositivo(0.075,new DispositivoInteligente(new Apagado()), new Heladera(false));
	Dispositivo lavarropasAutomaticoConCalentamiento = new Dispositivo(0.875,new DispositivoEstandar(2), new Lavarropas(5,true,true));
	Dispositivo lavarropasAutomaticoSinCalentamiento = new Dispositivo(0.175,new DispositivoInteligente(new Apagado()), new Lavarropas(5,true,false));
	Dispositivo lavarropasSemiAutomatico = new Dispositivo(0.1275,new DispositivoEstandar(2), new Lavarropas(5,false,false));
	Dispositivo ventiladorDePie = new Dispositivo(0.09,new DispositivoEstandar(2), new Ventilador(false));
	Dispositivo ventiladorDeTecho = new Dispositivo(0.06,new DispositivoInteligente(new Apagado()), new Ventilador(false));
	Dispositivo lamparaHalogenasPequenia = new Dispositivo(0.04,new DispositivoInteligente(new Apagado()), new Lampara(40,true));
	Dispositivo lamparaHalogenasMediana = new Dispositivo(0.06,new DispositivoInteligente(new Apagado()), new Lampara(60,true));
	Dispositivo lamparaHalogenasGrande = new Dispositivo(0.015,new DispositivoInteligente(new Apagado()), new Lampara(100,true));
	Dispositivo lamparaPequenia = new Dispositivo(0.011,new DispositivoInteligente(new Apagado()), new Lampara(11,false));
	Dispositivo lamparaMediana = new Dispositivo(0.015,new DispositivoInteligente(new Apagado()), new Lampara(15,false));
	Dispositivo lamparaGrande = new Dispositivo(0.02,new DispositivoInteligente(new Apagado()), new Lampara(20,false));
	Dispositivo pc = new Dispositivo(0.4,new DispositivoInteligente(new Apagado()), new Computadora(false));
	Dispositivo microondas = new Dispositivo(0.64,new DispositivoEstandar(2), new Microondas());

	
	public List<Dispositivo> dispositivos = new ArrayList<>(
			Arrays.asList(aireAcondicionadoPoderoso,
					aireAcondicionadoDebil,
					televisorTuboChico,
					televisorTuboGrande29,
					televisorTuboGrande30,
					televisorTuboGrande31,
					televisorTuboGrande32,
					televisorTuboGrande33,
					televisorTuboGrande34,
					televisorLCD,
					televisorTuboLEDChico,
					televisorTuboLEDMediano,
					televisorTuboLEDGrande,
					heladeraConFreezer,
					heladeraSinFreezer,
					lavarropasAutomaticoConCalentamiento,
					lavarropasAutomaticoSinCalentamiento,
					lavarropasSemiAutomatico,
					ventiladorDePie,
					ventiladorDeTecho,
					lamparaHalogenasPequenia,
					lamparaHalogenasMediana,
					lamparaHalogenasGrande,
					lamparaPequenia,
					lamparaMediana,
					lamparaGrande,
					pc,
					microondas
					));

	
}
