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
		
		double[] arrayConsumoPorHora = new double[unCliente.cantidadDispositivos()];
		double[] coeficientesRestriccion = new double[unCliente.cantidadDispositivos()];
		double[] coeficientesFuncion = new double[unCliente.cantidadDispositivos() + 1];
		dispositivo = 0;
		
		unCliente.getDispositivos().forEach(disp -> {
			arrayConsumoPorHora[dispositivo] = disp.getConsumoKWPorHora();
			coeficientesFuncion[dispositivo] = 1;
			coeficientesRestriccion[dispositivo] = 0;
			dispositivo++;
		});
		
		coeficientesFuncion[dispositivo] = 0;
		
		// Restriccion en base al consumo por hora de cada dispositivo y el consumo maximo para hogar eficiente
		LinearConstraint unaRestriccion = new LinearConstraint(arrayConsumoPorHora,Relationship.LEQ,consumoMaximoHogar);
		restricciones.add(unaRestriccion);
		
		dispositivo = 0;
		// Restricciones del problema -- Minimo y Maximo por dispositivo
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
		
		// Funcion -- coeficientes de funcion lineal
		LinearObjectiveFunction funcion = new LinearObjectiveFunction(coeficientesFuncion, 1);
			
		// resultado devuelve el consumo total en el mes y el consumo por dispositivo
		PointValuePair resultado = solver.optimize(new MaxIter(100),
				funcion,
				new LinearConstraintSet(restricciones),
				GoalType.MAXIMIZE,
				new NonNegativeConstraint(true)
				);		
		
		double[] resultados = resultado.getPoint();
		
		dispositivo = 0;
		
		unCliente.getDispositivos().forEach(disp -> {			
			disp.setTiempoQueSePuedeUtilizar(resultados[dispositivo]);
			dispositivo++;
		});
		
		double horasConsumidasEnUnMes = resultado.getValue();
		
		return horasConsumidasEnUnMes;
		}		
}
