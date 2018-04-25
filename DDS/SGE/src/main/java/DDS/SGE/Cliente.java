package DDS.SGE;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.json.*;

public class Cliente implements Usuario {
	private String nombre;
	private String apellido;
	private TipoDni tipoDni;
	private String numeroDni;
	private String telefono;
	private String domicilio;
	// Según el anuncio que mandaron a dds-jv está "prohibido" utilizar
	// java.Calendar
	private LocalDate fechaAltaServicio;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;

	public Cliente(String nombre, String apellido, TipoDni tipoDni, String numeroDni, String telefono, String domicilio,
			LocalDate fechaAltaServicio, List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDni = tipoDni;
		this.numeroDni = numeroDni;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.setDispositivos(dispositivos);
	}

	// Creo constructor para crear un Cliente a partir de un json.
	public Cliente(String json) {
		this.CargarDesdeJson(json);
	}

	public enum TipoDni {
		dni, ci, le, lc
	}

	public String getTipoDni() {
		return this.tipoDni.toString();
	}

	public String getNombre() {
		return this.nombre;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setTipoDni(String string) {
		tipoDni = TipoDni.valueOf(string);
	}

	public void setFechaAltaServicio(int anio, int mes, int dia) {
		this.fechaAltaServicio = this.fechaAltaServicio.withDayOfMonth(dia).withMonth(mes).withYear(anio);
	}

	public Stream<Dispositivo> getDispositivos() {
		return dispositivos.stream();
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
		this.categorizar();
	}

	public boolean algunDispositivoEncendido() {
		return getDispositivos().anyMatch(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantidadDeDispositivos() {
		return dispositivos.size();
	}

	public Stream<Dispositivo> dispositivosEncendidos() {
		return getDispositivos().filter(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantidadDispositivosEncendidos() {
		return (int) dispositivosEncendidos().count(); // Hago el casteo a Int porque el .count() me devuelve long.
	}

	public int cantidadDispositivosApagados() {
		return this.cantidadDeDispositivos() - this.cantidadDispositivosEncendidos();
	}

	public DoubleStream consumoDispositivosPorHora() {
		return getDispositivos().mapToDouble(dispositivo -> dispositivo.getConsumoKWPorHora());
	}

	public double consumoTotalPorHora() {
		return this.consumoDispositivosPorHora().sum();
	}

	// Tengo mis dudas aca, porque estoy asumiendo que un mes tiene 30 dias y no se
	// si es lo correcto.
	public double consumoTotalPorMes() {
		LocalDate localDate = LocalDate.now();
		int diasDelMesActual = localDate.lengthOfMonth();
		return this.consumoFinal(diasDelMesActual);
	}

	public double consumoTotalDeUnMesEspecifico(LocalDate fecha) {
		int diasDeTalMes = fecha.lengthOfMonth();
		return this.consumoFinal(diasDeTalMes);
	}

	public double consumoFinal(int mes) {
		return this.consumoTotalPorHora() * 24 * mes;
	}

	public void categorizar() {
		categoria = Arrays.stream(Categoria.values())
				.filter(categorias -> categorias.pertenece(this.consumoTotalPorMes())).findFirst().get();
	}

	public void CargarDesdeJson(String json) {
		JSONObject jsonObject = new JSONObject(json);
		this.nombre = jsonObject.getString("nombre");
		this.apellido = jsonObject.getString("apellido");
		this.setTipoDni(jsonObject.getString("tipoDni"));
		this.numeroDni = jsonObject.getString("numeroDni");
		this.telefono = jsonObject.getString("telefono");
		this.domicilio = jsonObject.getString("domicilio");
		// this.setFechaAltaServicio(jsonObject.getInt("anio"),
		// jsonObject.getInt("mes"), jsonObject.getInt("dia"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy"); // Uso un parser
		this.fechaAltaServicio = LocalDate.parse(jsonObject.getString("fechaAltaServicio"), formatter);
		;
	}
}