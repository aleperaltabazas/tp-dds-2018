package DDS.SGE;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
	
	Gson gson = new GsonBuilder().create();
	JSONParser parser = new JSONParser();
	byte[] encoded;
	
	Dispositivo crearDispositivo(String path) throws Exception {
		encoded = Files.readAllBytes(Paths.get(path));
		String json = new String(encoded);
		return gson.fromJson(json, Dispositivo.class);
	}
	
	Cliente crearCliente(String path) throws Exception {
		encoded = Files.readAllBytes(Paths.get(path));
		String json = new String(encoded);
		return gson.fromJson(json, Cliente.class);	

	}
	
	Administrador crearAdministrador(String path) throws Exception {
		encoded = Files.readAllBytes(Paths.get(path));
		String json = new String(encoded);
		return gson.fromJson(json, Administrador.class);
	}
}
