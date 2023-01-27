package com.di.entities;

import java.util.Date;

public class Libro {

	private int id;
	private String isbn;
	private Date fechaEdicion;
	private int almacenOld;
	private int almacenNew;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public int getAlmacenOld() {
		return almacenOld;
	}

	public void setAlmacenOld(int almacenOld) {
		this.almacenOld = almacenOld;
	}

	public int getAlmacenNew() {
		return almacenNew;
	}

	public void setAlmacenNew(int almacenNew) {
		this.almacenNew = almacenNew;
	}

}
