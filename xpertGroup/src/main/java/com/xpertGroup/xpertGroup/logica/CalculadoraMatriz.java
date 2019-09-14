package com.xpertGroup.xpertGroup.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import com.xpertGroup.xpertGroup.dominio.Matriz;
public class CalculadoraMatriz {

	private int n;

	private int m;

	private int contadorOperaciones = 0;
	private List<Matriz> matrices;

	public CalculadoraMatriz() {
	}

	public CalculadoraMatriz(int n, int m) {
		super();
		this.n = n;
		this.m = m;
	}

	public void actualizar(Matriz matriz) throws Exception {
		IntStream.range(0, matrices.size()).forEach(i -> {
			if (existeLaMatriz(matriz, i)) {
				matrices.get(i).setW(matriz.getW());
			}
		});
		contadorOperaciones++;
	}

	private boolean existeLaMatriz(Matriz matriz, int i) {
		return matrices.get(i).getX() == matriz.getX() && matrices.get(i).getY() == matriz.getY()
				&& matrices.get(i).getZ() == matriz.getZ();
	}

	public int consultar(Matriz intervalo1, Matriz intervalo2) throws Exception {
		List<Matriz> matricesASumar = new ArrayList<>();
		AtomicInteger matricesExistentes = new AtomicInteger(0);
		IntStream.range(0, matrices.size()).forEach(i -> {
			if(existeLaMatriz(intervalo1, i)) matricesExistentes.getAndIncrement();
			if(existeLaMatriz(intervalo2, i)) matricesExistentes.getAndIncrement();
		});
		if (matricesExistentes.intValue() == 2) {
			IntStream.range(intervalo1.getX() - 1, intervalo2.getX()).forEach(i -> matricesASumar.add(matrices.get(i)));
		}
		contadorOperaciones++;
		return matricesASumar.stream().map(m -> m.getW()).reduce(0, (a, b) -> a + b);
	}

	public void generarMatrices() {
		List<Matriz> matrices = new ArrayList<>();
		IntStream.range(1, n + 1).forEach(i -> matrices.add(new Matriz(i, i, i, 0)));
		this.matrices = matrices;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getContadorOperaciones() {
		return contadorOperaciones;
	}

	public void setContadorOperaciones(int contadorOperaciones) {
		this.contadorOperaciones = contadorOperaciones;
	}

	public List<Matriz> getMatrices() {
		return matrices;
	}

	public void setMatrices(List<Matriz> matrices) {
		this.matrices = matrices;
	}

}
