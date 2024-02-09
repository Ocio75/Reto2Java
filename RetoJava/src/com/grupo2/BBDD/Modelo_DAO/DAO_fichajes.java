package com.grupo2.BBDD.Modelo_DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.BBDD.Modelo_DTO.DTO_fichaje;
import com.grupo2.BBDD.conexion.Conexion;
import com.grupo2.Interfaces.Patron_DAO;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;

public class DAO_fichajes implements Patron_DAO<DTO_fichaje> {

	private Connection conexion;

	public DAO_fichajes() {
		this.conexion = Conexion.getInstancia().getCon();
	}

	@Override
	public boolean insertar(DTO_fichaje fichaje) {
		String query = "INSERT INTO fichajes ( codigo_empleado, hora_entrada, hora_salida, fecha) VALUES (?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
			preparedStatement.setInt(1, fichaje.getCodigo_empleado());
			preparedStatement.setTime(2, fichaje.getHora_entrada());
			preparedStatement.setTime(3, fichaje.getHora_salida());
			preparedStatement.setDate(4, fichaje.getFecha());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	 public boolean insertarFichajesMasivos() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate fechaInicio = LocalDate.parse("2023-08-01", formatter);
	        LocalDate fechaFin = LocalDate.now();

	        try {
	            while (!fechaInicio.isAfter(fechaFin)) {
	                String fechaActual = fechaInicio.format(formatter);
	        		DAO_empleados empleados = new DAO_empleados();

	                // Obtener lista de empleados
	        		ArrayList<DTO_empleados> empleadoe = empleados.listarTodos();

	                // Generar registros de fichajes para cada empleado
	                for (DTO_empleados empleado : empleadoe) {
	                    DTO_fichaje fichaje = new DTO_fichaje();
	                    fichaje.setCodigo_empleado(empleado.getDni());
	                    fichaje.setHora_entrada(Time.valueOf("08:00:00"));
	                    fichaje.setHora_salida(Time.valueOf("17:00:00"));
	                    fichaje.setFecha(Date.valueOf(fechaActual));

	                    insertar(fichaje);
	                }

	                fechaInicio = fechaInicio.plusDays(1); // Avanzar al siguiente día
	            }

	            return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	@Override
	public boolean borrar(Object pk) {
		String query = "DELETE FROM fichajes WHERE codigo_ficha = ?";

		try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
			preparedStatement.setInt(1, (int) pk);

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(DTO_fichaje fichaje) {
		String query = "UPDATE fichajes SET codigo_empleado = ?, hora_entrada = ?, hora_salida = ?, fecha = ? WHERE codigo_ficha = ?";

		try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
			preparedStatement.setInt(1, fichaje.getCodigo_empleado());
			preparedStatement.setTime(2, fichaje.getHora_entrada());
			preparedStatement.setTime(3, fichaje.getHora_salida());
			preparedStatement.setDate(4, fichaje.getFecha());
			preparedStatement.setInt(5, fichaje.getCodigo_ficha());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTO_fichaje buscar(Object pk) {
		String query = "SELECT * FROM fichajes WHERE codigo_ficha = ?";

		try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
			preparedStatement.setInt(1, (int) pk);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int codigo_ficha = resultSet.getInt("codigo_ficha");
					int codigo_empleado = resultSet.getInt("codigo_empleado");
					Time hora_entrada = resultSet.getTime("hora_entrada");
					Time hora_salida = resultSet.getTime("hora_salida");
					Date fecha = resultSet.getDate("fecha");

					return new DTO_fichaje(codigo_ficha, codigo_empleado, hora_entrada, hora_salida, fecha);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<DTO_fichaje> listarTodos() {
		ArrayList<DTO_fichaje> listaFichajes = new ArrayList<>();
		String query = "SELECT * FROM fichajes order by fecha DESC , hora_entrada ASc";

		try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int codigo_ficha = resultSet.getInt("codigo_ficha");
				int codigo_empleado = resultSet.getInt("codigo_empleado");
				Time hora_entrada = resultSet.getTime("hora_entrada");
				Time hora_salida = resultSet.getTime("hora_salida");
				Date fecha = resultSet.getDate("fecha");

				DTO_fichaje fichaje = new DTO_fichaje(codigo_ficha, codigo_empleado, hora_entrada, hora_salida, fecha);
				listaFichajes.add(fichaje);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFichajes;
	}

	@Override
	public void cargarTabla(JTable tabla) {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);

		modelo.addColumn("Código Ficha");
		modelo.addColumn("Código Empleado");
		modelo.addColumn("Hora Entrada");
		modelo.addColumn("Hora Salida");
		modelo.addColumn("Fecha");

		ArrayList<DTO_fichaje> listaFichajes = listarTodos();

		for (DTO_fichaje fichaje : listaFichajes) {
			Object[] fila = { fichaje.getCodigo_ficha(), fichaje.getCodigo_empleado(), fichaje.getHora_entrada(),
					fichaje.getHora_salida(), fichaje.getFecha() };
			modelo.addRow(fila);
		}
	}

	public void cargarTablaPorEmpleado(JTable tabla, int dni) {
		Date temporal = null;
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		modelo.addColumn("Codigo fichage");

		modelo.addColumn("Fecha");
		modelo.addColumn("Hora Entrada");
		modelo.addColumn("Hora Salida");
		// modelo.addColumn("Horas");

		ArrayList<DTO_fichaje> listaFichajes = listarTodos();

		for (DTO_fichaje fichaje : listaFichajes) {
			if (fichaje.getCodigo_empleado() == dni) {
				if (fichaje.getFecha().equals(temporal)) {
					Object[] fila2 = { fichaje.getCodigo_ficha(), null, fichaje.getHora_entrada(),
							fichaje.getHora_salida() };
					modelo.addRow(fila2);

				} else if (fichaje.getFecha() != temporal) {
					temporal = fichaje.getFecha();
					Object[] fila = { fichaje.getCodigo_ficha(), fichaje.getFecha(), null, null };
					modelo.addRow(fila);
					Object[] fila2 = { fichaje.getCodigo_ficha(), null, fichaje.getHora_entrada(),
							fichaje.getHora_salida() };
					modelo.addRow(fila2);

				}
			}
		}
		JObjetos.tabla.establecerAnchoCeroColumna(tabla, 0);
	}
	  public String[] listaAnios() {
	        String query = "SELECT DISTINCT YEAR(fecha) AS anio FROM fichajes";
	        List<String> anios = new ArrayList<>();

	        try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int anio = resultSet.getInt("anio");
	                anios.add(String.valueOf(anio));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return anios.toArray(new String[0]);
	    }
	public void cargarTablaPorEmpleadoYFecha(JTable tabla, int dni, java.util.Date fecha2) {
	    // Calculate the date 7 days ago
	    Date fin = new Date(fecha2.getTime() - 7 * 24 * 60 * 60 * 1000);

	    DefaultTableModel modelo = new DefaultTableModel();
	    tabla.setModel(modelo);
	    tabla.setDefaultEditor(Object.class, null);
	    tabla.getTableHeader().setReorderingAllowed(false);
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    modelo.addColumn("Codigo fichage");
	    modelo.addColumn("Fecha");
	    modelo.addColumn("Hora Entrada");
	    modelo.addColumn("Hora Salida");

	    String query = "SELECT * FROM fichajes WHERE codigo_empleado = ? order by fecha desc;";

	    try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
	        preparedStatement.setInt(1, dni);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            Date temporal = null;

	            while (resultSet.next()) {
	                int codigo_ficha = resultSet.getInt("codigo_ficha");
	                Time hora_entrada = resultSet.getTime("hora_entrada");
	                Time hora_salida = resultSet.getTime("hora_salida");
	                Date fecha = resultSet.getDate("fecha");
			

	                if (fecha.after(fin) && fecha.before(fecha2)) {
	                    if (fecha.equals(temporal)) {
	                        Object[] fila2 = { codigo_ficha, null, hora_entrada, hora_salida };
	                        modelo.addRow(fila2);
	                    } else if (!fecha.equals(temporal)) {
	                        temporal = fecha;
	                        Object[] fila = { codigo_ficha, fecha, null, null };
	                        modelo.addRow(fila);
	                        Object[] fila2 = { codigo_ficha, null, hora_entrada, hora_salida };
	                        modelo.addRow(fila2);
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    JObjetos.tabla.establecerAnchoCeroColumna(tabla, 0);
	}


	public int fichagesAbiertos(int dni) {
		ArrayList<DTO_fichaje> listaFichajes = listarTodos();
		Time hora = new Time(0, 0, 0);
		int codigofich = -1;
		for (DTO_fichaje fichaje : listaFichajes) {
			if (fichaje.getCodigo_empleado() == dni) {
				if (fichaje.getHora_salida().equals(hora)) {
					codigofich = fichaje.getCodigo_ficha();
				}
			}
		}
		return codigofich;
	}

}
