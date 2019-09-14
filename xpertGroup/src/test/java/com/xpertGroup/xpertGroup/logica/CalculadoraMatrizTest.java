package com.xpertGroup.xpertGroup.logica;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xpertGroup.xpertGroup.dominio.Matriz;

public class CalculadoraMatrizTest {
	
	@Test
	public void generarMatricesTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(5, 4);
		calculadoraMatriz.generarMatrices();
		assertEquals(5, calculadoraMatriz.getMatrices().size());
	}
	
	@Test
	public void consultarTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(3, 3);
		calculadoraMatriz.generarMatrices();
		Matriz intervalo1 = new Matriz(1,1,1);
		Matriz intervalo2 = new Matriz(3,3,3);
		assertEquals(0, calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
	
	@Test
	public void consultarDespuesDeActualizarTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(3, 3);
		calculadoraMatriz.generarMatrices();
		Matriz matriz = new Matriz(1,1,1,4);
		Matriz intervalo1 = new Matriz(1,1,1);
		Matriz intervalo2 = new Matriz(3,3,3);
		calculadoraMatriz.actualizar(matriz);
		assertEquals(4, calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
	
	@Test
	public void consultarDespuesDeActualizarFueraDeRangoTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(5, 4);
		calculadoraMatriz.generarMatrices();
		Matriz matriz = new Matriz(1,1,1,4);
		Matriz matriz2 = new Matriz(4,4,4,4);
		Matriz intervalo1 = new Matriz(1,1,1);
		Matriz intervalo2 = new Matriz(3,3,3);
		calculadoraMatriz.actualizar(matriz);
		calculadoraMatriz.actualizar(matriz2);
		assertEquals(4, calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
	
	@Test
	public void consultarInexistenteTest() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(3, 3);
		calculadoraMatriz.generarMatrices();
		Matriz matriz = new Matriz(1,1,1,4);
		Matriz intervalo1 = new Matriz(1,1,1);
		Matriz intervalo2 = new Matriz(3,2,3);
		calculadoraMatriz.actualizar(matriz);
		assertEquals(0, calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
	
	@Test
	public void consultarMatricesMismoValor() throws Exception {
		CalculadoraMatriz calculadoraMatriz = new CalculadoraMatriz(3, 3);
		calculadoraMatriz.generarMatrices();
		Matriz matriz = new Matriz(1,1,1,4);
		Matriz intervalo1 = new Matriz(1,1,1);
		Matriz intervalo2 = new Matriz(1,1,1);
		calculadoraMatriz.actualizar(matriz);
		assertEquals(4, calculadoraMatriz.consultar(intervalo1, intervalo2));
	}
}
