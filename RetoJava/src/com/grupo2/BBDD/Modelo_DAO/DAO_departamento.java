package com.grupo2.BBDD.Modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo2.BBDD.Modelo_DTO.DTO_departamentos;
import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.BBDD.conexion.Conexion;
import com.grupo2.Interfaces.Patron_DAO;

public class DAO_departamento implements Patron_DAO<DTO_departamentos> {

	private Connection conexion;

	public DAO_departamento() {
		this.conexion = Conexion.getInstancia().getCon();
	}

	@Override
	public boolean insertar(DTO_departamentos departamento) {
		String query = "INSERT INTO departamentos (Nombre, Descripcion) VALUES ( ?, ?)";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, departamento.getNombre());
			preparedStatement.setString(2, departamento.getDescripcion());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean borrar(Object pk) {
		String query = "DELETE FROM departamentos WHERE codigo_departamento = ?";

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
	public boolean actualizar(DTO_departamentos departamento) {
		String query = "UPDATE departamentos SET Nombre = ?, Descripcion = ? WHERE codigo_departamento = ?";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, departamento.getNombre());
			preparedStatement.setString(2, departamento.getDescripcion());
			preparedStatement.setInt(3, departamento.getCodigo_departamento());

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTO_departamentos buscar(Object pk) {
		String query = "SELECT * FROM departamentos WHERE codigo_departamento = ?";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, (int) pk);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int codigo_departamento = resultSet.getInt("codigo_departamento");
					String nombre = resultSet.getString("Nombre");
					String descripcion = resultSet.getString("Descripcion");

					return new DTO_departamentos(codigo_departamento, nombre, descripcion);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<DTO_departamentos> listarTodos() {
		ArrayList<DTO_departamentos> listaDepartamentos = new ArrayList<>();
		String query = "SELECT * FROM departamentos";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int codigo_departamento = resultSet.getInt("codigo_departamento");
				String nombre = resultSet.getString("Nombre");
				String descripcion = resultSet.getString("Descripcion");

				DTO_departamentos departamento = new DTO_departamentos(codigo_departamento, nombre, descripcion);
				listaDepartamentos.add(departamento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDepartamentos;
	}

	@Override
	public void cargarTabla(JTable tabla) {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);

		modelo.addColumn("Código Departamento");
		modelo.addColumn("Nombre");
		modelo.addColumn("Descripción");
		modelo.addColumn("Nº de Empleados");

		ArrayList<DTO_departamentos> listaDepartamentos = listarTodos();

		for (DTO_departamentos departamento : listaDepartamentos) {
			Object[] fila = { departamento.getCodigo_departamento(), departamento.getNombre(),
					departamento.getDescripcion(),contar(departamento.getCodigo_departamento()) };
			modelo.addRow(fila);
		}
	}

	private int contar(int codDep) {
		DAO_empleados empleados = new DAO_empleados();
		int cont =0;
		ArrayList<DTO_empleados> listaEmpleados =empleados.listarTodos();
		for(DTO_empleados empleado:listaEmpleados) {
			if(empleado.getCodigo_departamento()==codDep) {
				cont++;
			}
		}
		return cont;
	}

	public String[] arrayNombres() {
		ArrayList<DTO_departamentos> listaDepartamentos = listarTodos();
		String[] nombres = new String[listaDepartamentos.size()];
		int i = 0;
		for (DTO_departamentos departamento : listaDepartamentos) {
			String cadena = departamento.getCodigo_departamento() + "-" + departamento.getNombre();
			nombres[i] = (cadena.trim());
			i++;
		}
		return nombres;
	}
}
