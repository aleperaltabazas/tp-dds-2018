package DDS.SGE;

import java.awt.List;
import java.util.ArrayList;

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
	
	int consumoMaximoHogar= 612;
	int dispositivo;
	
	double Calcular(Cliente unCliente) {
		SimplexSolver solver = new SimplexSolver();
		ArrayList<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();			
		
		double[] arrayPotencias = new double[unCliente.cantidadDispositivos()];
		double[] coeficientesRestriccion = new double[unCliente.cantidadDispositivos()];
		double[] coeficientesFuncion = new double[unCliente.cantidadDispositivos() + 1];
		
		inicializarPotenciasYCoeficientes(unCliente, arrayPotencias, coeficientesRestriccion, coeficientesFuncion);
		
		// Restriccion en base al consumo por hora de cada dispositivo y el consumo maximo para hogar eficiente
		LinearConstraint unaRestriccion = new LinearConstraint(arrayPotencias,Relationship.LEQ,consumoMaximoHogar);
		restricciones.add(unaRestriccion);
		
		// Restricciones del problema -- Minimo y Maximo por dispositivo
		agregarRestriccionesPorDispositivo(unCliente, restricciones, coeficientesRestriccion);
		
		// Funcion -- coeficientes de funcion lineal
		LinearObjectiveFunction funcion = new LinearObjectiveFunction(coeficientesFuncion, 0);
			
		// resultado devuelve el consumo total en el mes y el consumo por dispositivo
		PointValuePair resultado = solver.optimize(new MaxIter(100),
				funcion,
				new LinearConstraintSet(restricciones),
				GoalType.MAXIMIZE,
				new NonNegativeConstraint(true)
				);		
		
		double[] resultados = resultado.getPoint();
		
		setearTiempoRecomendadoPorDispositivo(unCliente, resultados);
		
		double horasConsumidasEnUnMes = resultado.getValue();
		
		return horasConsumidasEnUnMes;
		}

	private void setearTiempoRecomendadoPorDispositivo(Cliente unCliente, double[] resultados) {
		dispositivo = 0;
		
		unCliente.getDispositivos().forEach(disp -> {			
			disp.setTiempoQueSePuedeUtilizar(resultados[dispositivo]);
			dispositivo++;
		});
	}

	private void agregarRestriccionesPorDispositivo(Cliente unCliente, ArrayList<LinearConstraint> restricciones,
			double[] coeficientesRestriccion) {
		dispositivo = 0;
		
		unCliente.getDispositivos().forEach(disp -> {
			
			coeficientesRestriccion[dispositivo] = 1;
			
			restricciones.
				add(new LinearConstraint(coeficientesRestriccion,
						Relationship.GEQ, disp.getFabricante().usoMensualMinimo()));
			restricciones.
				add(new LinearConstraint(coeficientesRestriccion,
						Relationship.LEQ, disp.getFabricante().usoMensualMaximo()));
			
			coeficientesRestriccion[dispositivo] = 0;
			dispositivo++;
		});
	}

	private void inicializarPotenciasYCoeficientes(Cliente unCliente, double[] arrayPotencias,
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
}
