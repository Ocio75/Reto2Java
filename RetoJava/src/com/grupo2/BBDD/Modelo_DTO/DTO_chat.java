package com.grupo2.BBDD.Modelo_DTO;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DTO_chat {
	private int dni;
	private String mensaje;
	private Time hora;
	private Date fecha;
	
	public DTO_chat() {
		super();
	}
	public DTO_chat(int dni, String mensaje, Time hora, Date fecha) {
		super();
		this.dni = dni;
		this.mensaje = mensaje;
		this.hora = hora;
		this.fecha = fecha;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni, fecha, hora, mensaje);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTO_chat other = (DTO_chat) obj;
		return dni == other.dni && Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora)
				&& Objects.equals(mensaje, other.mensaje);
	}
}
