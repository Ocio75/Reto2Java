package com.grupo2.BBDD.Modelo_DTO;

import java.util.Objects;

public class DTO_departamentos {
	private int codigo_departamento;
	private String nombre;
	private String descripcion;
	
	
	public DTO_departamentos() {
		super();
	}

	public DTO_departamentos(int codigo_departamento, String nombre, String descripcion) {
		super();
		this.codigo_departamento = codigo_departamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public DTO_departamentos( String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getCodigo_departamento() {
		return codigo_departamento;
	}

	public void setCodigo_departamento(int codigo_departamento) {
		this.codigo_departamento = codigo_departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_departamento, descripcion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTO_departamentos other = (DTO_departamentos) obj;
		return codigo_departamento == other.codigo_departamento && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
}
