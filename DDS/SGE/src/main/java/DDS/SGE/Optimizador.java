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
	
	int posicion;
	int consumoMaximoHogar= 612;
	int dispositivo;
	
	double Calcular(Cliente unCliente) {
		SimplexSolver solver = new SimplexSolver();
		ArrayList<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
		
		double[] arrayConsumoPorHora = new double[unCliente.cantidadDispositivos()];
		double[] coeficientes = {0,1}; // TODO
		posicion = 0;
		
		unCliente.getDispositivos().forEach(disp -> {
			arrayConsumoPorHora[posicion] = disp.getConsumoKWPorHora();
			posicion++;
		});
		
		// Restriccion en base al consumo por hora de cada dispositivo y el consumo maximo para hogar eficiente
		LinearConstraint unaRestriccion = new LinearConstraint(arrayConsumoPorHora,Relationship.LEQ,consumoMaximoHogar);
		restricciones.add(unaRestriccion);
		
		// Restricciones del problema -- Minimo y Maximo por dispositivo
		unCliente.getDispositivos().forEach(disp -> {restricciones.
				add(new LinearConstraint(coeficientes,Relationship.GEQ, disp.getFabricante().usoMensualMinimo()));
			restricciones.
				add(new LinearConstraint(coeficientes,Relationship.LEQ, disp.getFabricante().usoMensualMaximo()));
		});
		
		// Funcion en base al consumo por mes de cada dispositivo y el consumo maximo para hogar eficiente
		LinearObjectiveFunction funcion = new LinearObjectiveFunction(coeficientes, 1);
			
		// resultado devuelve el co
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
