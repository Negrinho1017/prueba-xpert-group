package com.xpertGroup.xpertGroup.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xpertGroup.xpertGroup.dominio.Matriz;
import com.xpertGroup.xpertGroup.logica.CalculadoraMatriz;

@RestController
@RequestMapping(value = "/")
public class MainController {
	
	CalculadoraMatriz calculadoraMatriz;

	@RequestMapping(value = "/creacion-resultados", method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public CalculadoraMatriz crearCalculadoraMatrices(@RequestBody CalculadoraMatriz calculadoraMatriz) {
		this.calculadoraMatriz = calculadoraMatriz;
		this.calculadoraMatriz.generarMatrices();
		return this.calculadoraMatriz;
	}
	
	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public int consultar(@RequestParam int x1, @RequestParam int y1, @RequestParam int z1,
			@RequestParam int x2, @RequestParam int y2, @RequestParam int z2) throws Exception {
		Matriz intervalo1 = new Matriz(x1, y1, z1);
		Matriz intervalo2 = new Matriz(x2, y2, z2);
		return this.calculadoraMatriz.consultar(intervalo1, intervalo2);
	}
	
	@RequestMapping(value = "/actualizacion", method = RequestMethod.PUT)
	@ResponseBody
	@CrossOrigin
	public Matriz actualizar(@RequestBody Matriz matriz) throws Exception {
		this.calculadoraMatriz.actualizar(matriz);
		return matriz;
	}
}
