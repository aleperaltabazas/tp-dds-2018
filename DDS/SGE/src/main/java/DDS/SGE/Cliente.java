package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.json.*;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.Interesado;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;

@Entity
public class Cliente {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@OneToMany()
	@JoinColumn(name="id")
	private List<Dispositivo> dispositivos;
	
	private String nombre;
	private String apellido;
	private TipoDni tipoDni;
	private String numeroDni;
	private String telefono;
	private String domicilio;
	private LocalDateTime fechaAltaServicio;
	private Categoria categoria;	
	//private Zona zona; --- YA NO VA
	private Transformador transformador; // = this.conectarATransformador(); Ya se inicializa con transformador
	int puntos;
	private InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos = new InteresadoEnNuevosDispositivos();
	private InteresadoEnAdaptaciones interesadoEnAdaptaciones = new InteresadoEnAdaptaciones();

	public Cliente(String nombre, String apellido, TipoDni tipoDni, String numeroDni, String telefono, String domicilio,
			LocalDateTime fechaAltaServicio, List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDni = tipoDni;
		this.numeroDni = numeroDni;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = Categoria.R1;
		this.dispositivos = new ArrayList<Dispositivo>();
		this.setDispositivos(dispositivos);
		//this.zona = zona; --- YA NO VA
	}

	public enum TipoDni {
		DNI, CI, LE, LC
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
		for (Dispositivo disp : dispositivos){
			agregarDispositivo(disp);
		}
	}
	public Transformador getTransformador() {
		return transformador;
	}
	
	public void setTransformador(Transformador nuevoTransformador) {
		transformador = nuevoTransformador;
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.add(dispositivo);
		this.interesadoEnNuevosDispositivos.sucedio(this, dispositivo);
	}

	public boolean algunDispositivoEncendido() {
		return getDispositivos().anyMatch(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantidadDispositivos() {
		return dispositivos.size();
	}

	public int cantidadDispositivosEncendidos() {
		return (int) this.dispositivosEncendidos().count(); //Hago el casteo a Int porque el .count() me devuelve long.
	}
	
	public Stream<Dispositivo> dispositivosEncendidos() {
		return getDispositivos().filter(dispositivo ->dispositivo.estaEncendido());
	}
	
	public int cantidadDispositivosApagados() {
		return this.cantidadDispositivos() - this.cantidadDispositivosEncendidos();
	}

	public DoubleStream consumoDispositivosDiarioEstimado() {
		return getDispositivos().mapToDouble(dispositivo -> dispositivo.consumoDiarioEstimado());
	}

	public double consumoTotalEstimadoDiario() {
		return this.consumoDispositivosDiarioEstimado().sum();
	}

	public double consumoTotalPorMes() {
		LocalDate localDate = LocalDate.now();
		int diasDelMesActual = localDate.lengthOfMonth();
		return this.consumoFinalEstimado(diasDelMesActual);
	}

	public double consumoTotalDeUnMesEspecifico(LocalDate fecha) {
		int diasDeTalMes = fecha.lengthOfMonth();
		return this.consumoFinalEstimado(diasDeTalMes);
	}

	public double consumoFinalEstimado(int diasDelMes) {
		return this.consumoTotalEstimadoDiario() * diasDelMes;
	}

	public void recategorizar() {
		categoria = Arrays.stream(Categoria.values())
				.filter(categorias -> categorias.pertenece(this.consumoTotalPorMes())).findFirst().get();
	}
	
	public void agregarModuloAdaptadorA(Dispositivo dispositivo) {
		if(lePerteneceDispositivo(dispositivo)) {
			dispositivo.adaptarConModulo();
			this.interesadoEnAdaptaciones.sucedio(this,dispositivo);
		}
	}
	
	public boolean lePerteneceDispositivo(Dispositivo dispositivo) {
		return this.dispositivos.contains(dispositivo);
	}
	
	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}
	
	public Zona zona() {
		return this.getTransformador().getZona();
	}
	
	public boolean preteneceAZona(Zona unaZona) {
		return this.zona() == unaZona;
	}
	
	/*public Transformador conectarATransformador() { // Selecciona transformador al azar para conectarse
		Transformador nuevoTransformador = zona.getTransformadores().get(new Random().nextInt(zona.getTransformadores().size()));
		if (nuevoTransformador != transformador) {
			nuevoTransformador.agregarCliente(this);
			return nuevoTransformador;
		} else {
			return transformador;
		}		
	}*/ // ESTO YA NO VA
	
	public void conectarseAEsteTransformador(Transformador nuevoTransformador) {
		transformador = nuevoTransformador;
		nuevoTransformador.agregarCliente(this);
	}
	
	public double consultarUsoOptimo() {
		Optimizador optimizador = new Optimizador();
		double consumoOptimoPorMesEnHoras = optimizador.Calcular(this);
		System.out.format("El consumo optimo por mes en horas es %f\n", consumoOptimoPorMesEnHoras);
		return consumoOptimoPorMesEnHoras;
	}
	
}

