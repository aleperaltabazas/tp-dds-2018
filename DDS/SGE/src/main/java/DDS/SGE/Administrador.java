package DDS.SGE;

import java.time.LocalDate;

import org.json.JSONObject;

public class Administrador implements Usuario {
	private String nombre;
	private String apellido;
	private String domicilio;
	private LocalDate fechaAltaSistema;
	private int idAdmin;
	
	
	public Administrador (String nombre, String apellido, String domicilio, LocalDate fechaAltaSistema, int idAdmin){
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.fechaAltaSistema = fechaAltaSistema;
		this.idAdmin = idAdmin;
	}
	
	public Administrador(String json) {
		this.CargarDesdeJson(json);
	}
	
	
	public void setFechaAltaSistema(int anio, int mes, int dia) {
		this.fechaAltaSistema = this.fechaAltaSistema.withDayOfMonth(dia).withMonth(mes).withYear(anio);
	}
	
	public int cantidadDeMesesComoAdmin() {
		//TODO Falta implementar
		return 1;
	}


	public void CargarDesdeJson(String json) {
		JSONObject jsonObject = new JSONObject(json);
		this.nombre = jsonObject.getString("nombre");
		this.apellido = jsonObject.getString("apellido");
		this.domicilio = jsonObject.getString("domicilio");
		this.setFechaAltaSistema(jsonObject.getInt("anio"), jsonObject.getInt("mes"), jsonObject.getInt("dia"));
		this.idAdmin = jsonObject.getInt("idAdmin");
		
	}
	
}
