package com.xpertGroup.xpertGroup.logica;
import org.junit.Test;

import com.xpertGroup.xpertGroup.dominio.Matriz;

public class CalculadoraMatrizTest {
	
	@Test
	public void generarMatricesTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(4, 4);
		calculadoraMatriz.generarMatrices();
		Matriz matriz = new Matriz(1, 1, 1, 5);
		Matriz matriz2 = new Matriz(3, 3, 3, 6);
		Matriz matriz3 = new Matriz(4, 4, 4, 6);
		Matriz intervalo1 = new Matriz(1, 1, 1);
		Matriz intervalo2 = new Matriz(3, 3, 3);
		calculadoraMatriz.actualizar(matriz);
		calculadoraMatriz.actualizar(matriz2);	
		calculadoraMatriz.actualizar(matriz3);
		
		
		
		
		System.out.println(calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
}
