package com.grupo2.BBDD.Modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo2.BBDD.Modelo_DTO.DTO_chat;
import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.BBDD.conexion.Conexion;
import com.grupo2.Interfaces.Patron_DAO;

public class DAO_chat implements Patron_DAO<DTO_chat> {

	private Connection conexion;

	public DAO_chat() {
		this.conexion = Conexion.getInstancia().getCon();
	}

	@Override
	public boolean insertar(DTO_chat chat) {
		String query = "INSERT INTO chat (dni, mensaje, hora, fecha) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, chat.getDni());
			preparedStatement.setString(2, chat.getMensaje());
			preparedStatement.setTime(3, chat.getHora());
			preparedStatement.setDate(4, chat.getFecha());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean borrar(Object pk) {
		String query = "DELETE FROM chat WHERE dni = ?";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, (int) pk);

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(DTO_chat chat) {
		String query = "UPDATE chat SET mensaje = ?, hora = ?, fecha = ? WHERE dni = ?";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, chat.getMensaje());
			preparedStatement.setTime(2, chat.getHora());
			preparedStatement.setDate(3, chat.getFecha());
			preparedStatement.setInt(4, chat.getDni());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTO_chat buscar(Object pk) {
		String query = "SELECT * FROM chat WHERE dni = ?";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, (int) pk);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int dni = resultSet.getInt("dni");
					String mensaje = resultSet.getString("mensaje");
					Time hora = resultSet.getTime("hora");
					Date fecha = resultSet.getDate("fecha");

					return new DTO_chat(dni, mensaje, hora, fecha);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<DTO_chat> listarTodos() {
		ArrayList<DTO_chat> listaChats = new ArrayList<>();
		String query = "SELECT * FROM chat";
		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int dni = resultSet.getInt("dni");
				String mensaje = resultSet.getString("mensaje");
				Time hora = resultSet.getTime("hora");
				Date fecha = resultSet.getDate("fecha");

				DTO_chat chat = new DTO_chat(dni, mensaje, hora, fecha);
				listaChats.add(chat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaChats;
	}

	@Override
	public void cargarTabla(JTable tabla) {
		DAO_empleados empleados = new DAO_empleados();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Empleado");
		model.addColumn("Ciudad");
		model.addColumn("Pais");
		model.addColumn("Region");

		ArrayList<DTO_chat> listaChats = listarTodos();
		ArrayList<DTO_empleados> listaEmpleados = empleados.listarTodos();

		for (DTO_chat chat : listaChats) {
			for (DTO_empleados empleado : listaEmpleados) {
				if (empleado.getDni() == chat.getDni()) {
					Object[] fila = { empleado.getNombre(), chat.getMensaje(), chat.getHora(), chat.getFecha() };
					model.addRow(fila);
					return;
				}
			}

		}

		tabla.setModel(model);
	}
}
