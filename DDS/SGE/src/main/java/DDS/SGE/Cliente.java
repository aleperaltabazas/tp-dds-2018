package DDS.SGE;


import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

public class Cliente implements Usuario {
	String nombre;
	String apellido;
	TipoDni tipoDni;
	String numeroDni;
	String telefono;
	String domicilio;
	Calendar fechaAltaServicio;
	Categoria categoria;
	List<Dispositivo> dispositivos;


	public Cliente(String nombre, String apellido, TipoDni tipoDni, String numeroDni, String telefono, String domicilio, Calendar fechaAltaServicio, Categoria categoria, List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDni = tipoDni;
		this.numeroDni = numeroDni;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
	}
	
	public enum TipoDni{
		dni,
		ci,
		le,
		lc
	}
	
	public String getTipoDni() {
		return this.tipoDni.toString();
	}
	
	public void setTipoDni(String string) {
		tipoDni = TipoDni.valueOf(string);
	}

	public void setCategoria(Categoria nuevaCategoria) {
		categoria = nuevaCategoria;
	}
	
	public Stream<Dispositivo> getDispositivos() {
		return dispositivos.stream();
	}

	public boolean algunDispositivoEncendido() {
		return getDispositivos().anyMatch(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantidadDeDispositivos() {
		return dispositivos.size();
	}

	public int dispositivosEncendidos() {
		return (int) getDispositivos().filter(dispositivo ->dispositivo.estaEncendido()).count(); //Hago el casteo a Int porque el .count() me devuelve long.
	}

	public int dispositivosApagados() {
		return this.cantidadDeDispositivos() - this.dispositivosEncendidos();
	}

	/// PARA QUE DEVUELVA LA SUMA DE LOS CONSUMOS DE TODOS SUS DISPOSITIVOS
	/// Puede que nos convenga calcular directo el consumo por mes estimado, que
	/// seria lo mismo *24 * dias del mes pero no sé exactamente
	public double consumoTotalPorHora() {
		/// ...
		return 1; /// Desarrollar
	}

	public Cliente(String json) {
		JSONObject obj = new JSONObject(json);		
		nombre = obj.getString("nombre");
		apellido = obj.getString("apellido");
		setTipoDni(obj.getString("tipoDni"));
		numeroDni = obj.getString("numeroDni");
		telefono = obj.getString("telefono");
		domicilio = obj.getString("domicilio");
		fechaAltaServicio.set(obj.getInt("anio"), obj.getInt("mes"), obj.getInt("dia"));
		categoria = (Categoria) obj.get("categoria");
	};
}