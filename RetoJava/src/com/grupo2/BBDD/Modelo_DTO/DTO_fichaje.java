package com.grupo2.BBDD.Modelo_DTO;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class DTO_fichaje {
	private int codigo_ficha;
	private int codigo_empleado;
	private Time hora_entrada;
	private Time hora_salida;
	private Date fecha;

	public DTO_fichaje() {
		super();
	}

	public DTO_fichaje(int codigo_empleado, Time hora_entrada, Time hora_salida, Date fecha) {
		super();
		this.codigo_empleado = codigo_empleado;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		this.fecha = fecha;
	}

	public DTO_fichaje(int codigo_ficha, int codigo_empleado, Time hora_entrada, Date fecha) {
		this(codigo_ficha, codigo_empleado, hora_entrada, null, fecha);
	}

	public DTO_fichaje(int codigo_ficha, int codigo_empleado, Time hora_entrada, Time hora_salida, Date fecha) {
		super();
		this.codigo_ficha = codigo_ficha;
		this.codigo_empleado = codigo_empleado;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		this.fecha = fecha;
	}

	public void cerrarFichaje() {
		if (hora_entrada != null && hora_salida == null) {
			LocalTime horaActual = LocalTime.now();
			hora_salida = new Time(horaActual.getHour(),horaActual.getMinute(),0);
			//return true;
		} else {
			//return false;
		}
	}

	public int getCodigo_ficha() {
		return codigo_ficha;
	}

	public void setCodigo_ficha(int codigo_ficha) {
		this.codigo_ficha = codigo_ficha;
	}

	public int getCodigo_empleado() {
		return codigo_empleado;
	}

	public void setCodigo_empleado(int codigo_empleado) {
		this.codigo_empleado = codigo_empleado;
	}

	public Time getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(Time hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public Time getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(Time hora_salida) {
		this.hora_salida = hora_salida;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	@Override
	public String toString() {
		return "DTO_fichaje [codigo_ficha=" + codigo_ficha + ", codigo_empleado=" + codigo_empleado + ", hora_entrada="
				+ hora_entrada + ", hora_salida=" + hora_salida + ", fecha=" + fecha + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_empleado, codigo_ficha, fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTO_fichaje other = (DTO_fichaje) obj;
		return codigo_empleado == other.codigo_empleado && codigo_ficha == other.codigo_ficha
				&& Objects.equals(fecha, other.fecha);
	}

}
