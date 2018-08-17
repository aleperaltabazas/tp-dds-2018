package DDS.SGE;

import java.awt.Window.Type;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import DDS.SGE.Dispositivo.Dispositivo;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonBuilder {
	
	private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
    ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()).create();
	
	Dispositivo crearDispositivo(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));			
			return gson.fromJson(reader , Dispositivo.class);
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
	
	Administrador crearAdministrador(String path){
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
			return gson.fromJson(reader, Zona.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear la Zona", e);
		}
	}
	
	Transformador crearTransformador(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Transformador.class);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el Transformador", e);
		}
	}
}
