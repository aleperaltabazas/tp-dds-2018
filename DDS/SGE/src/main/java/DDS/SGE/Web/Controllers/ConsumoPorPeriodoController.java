package DDS.SGE.Web.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DDS.SGE.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsumoPorPeriodoController {
	public static ModelAndView obtener(Request req, Response res) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDateTime fechaInicio = LocalDateTime.parse(req.queryParams("fechaInicio"),formatter);
		LocalDateTime fechaFin = LocalDateTime.parse(req.queryParams("fechaFin"),formatter);
		
		//int consumo = 
		
		return new ModelAndView(null, "home.hbs");
	}
	public static ModelAndView mostrar(Request req, Response res) {
		return new ModelAndView(null, "consumo-por-periodo.hbs");
	}
}
