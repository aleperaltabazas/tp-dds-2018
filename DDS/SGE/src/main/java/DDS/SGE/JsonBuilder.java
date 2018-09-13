package DDS.SGE;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;

import DDS.SGE.Dispositivo.Dispositivo;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;

public class JsonBuilder {

	private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
			(JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime
					.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime())
			.create();

	Dispositivo crearDispositivo(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Dispositivo.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Dispositivo", e);
		}
	}

	Cliente crearCliente(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Cliente.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Cliente", e);
		}
	}

	Administrador crearAdministrador(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Administrador.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Administrador", e);
		}
	}

	Zona crearZona(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			Zona zona = gson.fromJson(reader, Zona.class);
			System.out.println("Zona creada correctamente");
			return zona;
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear la Zona", e);
		}
	}

	Transformador crearTransformador(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			Transformador transformador = gson.fromJson(reader, Transformador.class);
			System.out.println("Transformador creado correctamente");
			return transformador;
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Transformador", e);
		}
	}
}
