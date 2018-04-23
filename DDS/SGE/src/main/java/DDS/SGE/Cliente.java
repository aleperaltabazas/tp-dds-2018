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
	
	//Creo constructor para crear un Cliente a partir de un json.
	public Cliente(JSONObject json) {
		this.CargarDesdeJson(json);
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
	
	public void setFechaAltaServicio(int anio, int mes, int dia) {
		this.fechaAltaServicio.set(anio,mes,dia);
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

	public double consumoTotalPorHora() {
		return getDispositivos().mapToDouble(dispositivo -> dispositivo.getConsumoKWPorHora()).sum();
	}
	
	//Tengo mis dudas aca, porque estoy asumiendo que un mes tiene 30 dias y no se si es lo correcto.
	public double consumoTotalPorMes() {
		return (this.consumoTotalPorHora() * 24) * 30;
	}
	//TODO Falta poder recategorizar a los Clientes, según su consumo:
	/*  Hay algo que no está explícito en el enunciado de esta entrega, sino que está en la presentación, pero queremos
	 *  que también hagan para esta entrega. Queremos que implementen el mensaje para recategorizar un usuario. Es decir, que a partir
	 *  del consumo que tiene, elija la categoría a la que debería pertenecer y se la asigne. No es necesario que hagan la parte de que
	 *  eso ocurra automáticamente cada 3 meses todavía.
	 */

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
	}
}