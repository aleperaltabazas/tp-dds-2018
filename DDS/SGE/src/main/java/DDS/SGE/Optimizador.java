package DDS.SGE;

import java.awt.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.math3.*;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import DDS.SGE.Dispositivo.Dispositivo;

public class Optimizador {

	static int consumoMaximoHogar = 612;
	static int dispositivo;

	public static void accionarSobreDispositivosInfractores(Stream<Dispositivo> dispositivos) {
		obtenerDispositivosInfractores(dispositivos).forEach(d -> d.apagar());
	}

	public static Stream<Dispositivo> obtenerDispositivosInfractores(Stream<Dispositivo> dispositivos) {
		int horasDelMes = 24 * 30;
		return dispositivos.filter(d -> d.consumoTotalHaceNHoras(horasDelMes) > d.getTiempoQueSePuedeUtilizar());
	}

	private static void setearTiempoRecomendadoPorDispositivo(Cliente unCliente, double[] resultados) {
		dispositivo = 0;

		unCliente.getDispositivos().forEach(disp -> {
			disp.setTiempoQueSePuedeUtilizar(resultados[dispositivo]);
			System.out.format("El dispositivo %s se podrá utilizar %f horas como maximo\n", disp.toString(),
					resultados[dispositivo]);
			dispositivo++;
		});
	}

	private static void agregarRestriccionesPorDispositivo(Cliente unCliente, ArrayList<LinearConstraint> restricciones,
			double[] coeficientesRestriccion) {
		dispositivo = 0;

		unCliente.getDispositivos().forEach(disp -> {

			coeficientesRestriccion[dispositivo] = 1;

			restricciones.add(new LinearConstraint(coeficientesRestriccion, Relationship.GEQ,
					disp.getFabricante().usoMensualMinimo()));
			restricciones.add(new LinearConstraint(coeficientesRestriccion, Relationship.LEQ,
					disp.getFabricante().usoMensualMaximo()));

			coeficientesRestriccion[dispositivo] = 0;
			dispositivo++;
		});
	}

	private static void inicializarPotenciasYCoeficientes(Cliente unCliente, double[] arrayPotencias,
			double[] coeficientesRestriccion, double[] coeficientesFuncion) {
		dispositivo = 0;

		unCliente.getDispositivos().forEach(disp -> {
			arrayPotencias[dispositivo] = disp.getConsumoKWPorHora();
			coeficientesFuncion[dispositivo] = 1;
			coeficientesRestriccion[dispositivo] = 0;
			dispositivo++;
		});

		coeficientesFuncion[dispositivo] = 0;
	}

	public static double Calcular(Cliente unCliente) {
		ArrayList<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();

		int cantidadDeDispositivos = unCliente.cantidadDispositivos();

		double[] arrayPotencias = new double[cantidadDeDispositivos];
		double[] coeficientesRestriccion = new double[cantidadDeDispositivos];
		double[] coeficientesFuncion = new double[cantidadDeDispositivos + 1];

		inicializarPotenciasYCoeficientes(unCliente, arrayPotencias, coeficientesRestriccion, coeficientesFuncion);

		restringir(arrayPotencias, restricciones);
		LinearObjectiveFunction funcion = dameFuncion(coeficientesFuncion);

		agregarRestriccionesPorDispositivo(unCliente, restricciones, coeficientesRestriccion);

		PointValuePair resultado = optimizar(funcion, restricciones);

		setearTiempoRecomendadoPorDispositivo(unCliente, resultado.getPoint());

		return resultado.getValue();
	}

	private static PointValuePair optimizar(LinearObjectiveFunction unaFuncion,
			ArrayList<LinearConstraint> unasRestricciones) {
		SimplexSolver solver = new SimplexSolver();

		return solver.optimize(new MaxIter(100), unaFuncion, new LinearConstraintSet(unasRestricciones),
				GoalType.MAXIMIZE, new NonNegativeConstraint(true));
	}

	private static LinearObjectiveFunction dameFuncion(double[] unosCoeficientesDeFuncion) {
		return new LinearObjectiveFunction(unosCoeficientesDeFuncion, 0);
	}

	private static void restringir(double[] unArrayDePotencias, ArrayList<LinearConstraint> unasRestricciones) {
		LinearConstraint restriccion = new LinearConstraint(unArrayDePotencias, Relationship.LEQ, consumoMaximoHogar);
		unasRestricciones.add(restriccion);
	}
}
