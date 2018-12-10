package DDS.SGE.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;

import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Geoposicionamiento.Transformador;
import DDS.SGE.Geoposicionamiento.Zona;

public class JsonBuilder {

	private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
			(JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime
					.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime())
			.create();

	public DispositivoEstandar crearDispositivoEstandar(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, DispositivoEstandar.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Dispositivo", e);
		}
	}

	public Cliente crearCliente(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Cliente.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Cliente", e);
		}
	}

	public Administrador crearAdministrador(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Administrador.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Administrador", e);
		}
	}

	public Zona crearZona(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			Zona zona = gson.fromJson(reader, Zona.class);
			return zona;
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear la Zona", e);
		}
	}

	public Transformador crearTransformador(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			Transformador transformador = gson.fromJson(reader, Transformador.class);
			return transformador;
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Transformador", e);
		}
	}
}
