package DDS.SGE;

import java.util.Calendar;

import org.json.JSONObject;

public class Administrador implements Usuario {
	String nombre;
	String apellido;
	String domicilio;
	Calendar fechaAltaSistema;
	int idAdmin;
	
	
	public Administrador (String nombre, String apellido, String domicilio, Calendar fechaAltaSistema, int idAdmin){
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.fechaAltaSistema = fechaAltaSistema;
		this.idAdmin = idAdmin;
	}
	
	public Administrador(JSONObject json) {
		this.CargarDesdeJson(json);
	}
	

	public void setFechaAltaSistema(int anio, int mes, int dia) {
		this.fechaAltaSistema.set(anio,mes,dia);
	}


	public void CargarDesdeJson(JSONObject json) {		
		this.nombre = json.getString("nombre");
		this.apellido = json.getString("apellido");
		this.domicilio = json.getString("domicilio");
		this.setFechaAltaSistema(json.getInt("anio"), json.getInt("mes"), json.getInt("dia"));
		this.idAdmin = json.getInt("idAdmin");
		
	}
	
}
