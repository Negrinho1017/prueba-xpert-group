package com.xpertGroup.xpertGroup.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpertGroup.xpertGroup.dominio.Matriz;
import com.xpertGroup.xpertGroup.logica.CalculadoraMatriz;

public class MainControllerTest {

	MainController mainController = new MainController();
	
	CalculadoraMatriz calculadoraMatriz;
	
	@Test
	public void crearCalculadoraMatricesTest() {
		calculadoraMatriz = Mockito.mock(CalculadoraMatriz.class);
		Mockito.doNothing().when(calculadoraMatriz).generarMatrices();
		mainController.crearCalculadoraMatrices(calculadoraMatriz);
		assertEquals(calculadoraMatriz.getN(), mainController.crearCalculadoraMatrices(calculadoraMatriz).getN());
		assertEquals(calculadoraMatriz.getM(), mainController.crearCalculadoraMatrices(calculadoraMatriz).getM());
	}
	
	@Test
	public void consultarTest() throws Exception {
		calculadoraMatriz = Mockito.mock(CalculadoraMatriz.class);
		mainController.crearCalculadoraMatrices(calculadoraMatriz);
		Mockito.when(calculadoraMatriz.consultar(Mockito.any(Matriz.class), Mockito.any(Matriz.class))).thenReturn(5);
		assertEquals(5, mainController.consultar(1, 1, 1, 3, 3, 3));	
	}
	
	@Test
	public void actualizarTest() throws Exception {
		calculadoraMatriz = Mockito.mock(CalculadoraMatriz.class);
		mainController.crearCalculadoraMatrices(calculadoraMatriz);
		Mockito.doNothing().when(calculadoraMatriz).actualizar(Mockito.any(Matriz.class));
		assertEquals(2, mainController.actualizar(new Matriz(2,2,2,4)).getX());	
		assertEquals(2, mainController.actualizar(new Matriz(2,2,2,4)).getY());	
		assertEquals(2, mainController.actualizar(new Matriz(2,2,2,4)).getZ());	
		assertEquals(4, mainController.actualizar(new Matriz(2,2,2,4)).getW());	
	}
	
	@Test
	public void maximoLlamadosTest() throws Exception {
		calculadoraMatriz = Mockito.mock(CalculadoraMatriz.class);
		mainController.crearCalculadoraMatrices(calculadoraMatriz);
		Mockito.when(calculadoraMatriz.hayMaximoDeLlamados()).thenReturn(false);
		assertEquals(false, mainController.maximoLlamados());	
	}
}
