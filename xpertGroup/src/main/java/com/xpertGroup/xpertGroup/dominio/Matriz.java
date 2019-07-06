package com.xpertGroup.xpertGroup.dominio;

public class Matriz {
	private int x;
	private int y;
	private int z;
	private int w;

	public Matriz(int x, int y, int z, int w) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Matriz(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

}
