package DDS.SGE;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
	
	Gson gson = new GsonBuilder().create();
	JSONParser parser = new JSONParser();
	byte[] encoded;
	
	Dispositivo crearDispositivo(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader , Dispositivo.class);
		} catch (IOException e) {
			return null;
		}
	}
	
	Cliente crearCliente(String path) {
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Cliente.class);
		} catch (IOException e) {
			return null;
		}
	}
	
	Administrador crearAdministrador(String path){
		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			return gson.fromJson(reader, Administrador.class);
		} catch (IOException e) {
			return null;
		}
	}
}
