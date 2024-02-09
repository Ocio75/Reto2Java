package com.grupo2.Ventanas.otros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.DAO_empleados;
import com.grupo2.BBDD.Modelo_DAO.DAO_fichajes;
import com.grupo2.Utiles.Archivos;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JMonthChooser;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class DateMesAnio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox CBEmpleado;
	private JMonthChooser monthChooser;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1;
	private JComboBox CBAnios;

	public DateMesAnio(int codigo) {
		setResizable(false);
		DAO_fichajes fichajes = new DAO_fichajes();
		DAO_empleados empleados = new DAO_empleados();

		setBounds(100, 100, 450, 328);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("SELECTOR DE MES Y A\u00D1O");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 416, 41);
		contentPanel.add(lblNewLabel);

		CBAnios = new JComboBox();
		CBAnios.setBounds(239, 138, 138, 41);
		contentPanel.add(CBAnios);
		JObjetos.comboBox.llenarCombo(fichajes.listaAnios(), CBAnios);

		lblNewLabel_1 = new JLabel("Mes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(64, 115, 82, 22);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("A\u00F1o");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(239, 115, 82, 22);
		contentPanel.add(lblNewLabel_1_1);

		monthChooser = new JMonthChooser();
		monthChooser.setBounds(64, 138, 138, 41);
		contentPanel.add(monthChooser);

		CBEmpleado = new JComboBox();
		CBEmpleado.setBounds(64, 222, 138, 41);
		contentPanel.add(CBEmpleado);
		JObjetos.comboBox.llenarCombo(empleados.arrayNombres(), CBEmpleado);

		lblNewLabel_1_1_1 = new JLabel("Empleado");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(64, 199, 82, 22);
		contentPanel.add(lblNewLabel_1_1_1);

		JButton btnNewButton = new JButton("Generar Infrome");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> hashMap = new HashMap<>();
				if (codigo == 1) {
					hashMap.put("anio", Integer.parseInt(CBAnios.getSelectedItem().toString()));
					int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt == 0) {
						JObjetos.JReports.mostrarReporteParametros("fichajes\\InfromeAnualTodosEmpleados.jasper",
								hashMap);
					} else if (opt == 1) {

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRutaParametros(
									"fichajes\\InfromeAnualTodosEmpleados.jasper", rutaYNombre[0], hashMap);

						}
					} else {

					}
				} else if (codigo == 2) {
					DAO_empleados empleados = new DAO_empleados();
					String debolucion = CBEmpleado.getSelectedItem().toString();
					String[] partes = debolucion.split("-");
					String dni = partes[0].trim();
					hashMap.put("mes", monthChooser.getMonth() + 1);
					hashMap.put("anio", Integer.parseInt(CBAnios.getSelectedItem().toString()));
					hashMap.put("dni", Integer.parseInt(dni));
					int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt == 0) {
						JObjetos.JReports.mostrarReporteParametros("fichajes\\fichajesPorMesAnioEmpleado.jasper",
								hashMap);
					} else if (opt == 1) {

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRutaParametros(
									"fichajes\\fichajesPorMesAnioEmpleado.jasper", rutaYNombre[0], hashMap);

						}
					} else {

					}
				} else if (codigo == 3) {// anual por empleado
					DAO_empleados empleados = new DAO_empleados();
					String debolucion = CBEmpleado.getSelectedItem().toString();
					String[] partes = debolucion.split("-");
					String dni = partes[0].trim();
					hashMap.put("mes", monthChooser.getMonth() + 1);
					hashMap.put("anio", Integer.parseInt(CBAnios.getSelectedItem().toString()));
					hashMap.put("dni", Integer.parseInt(dni));
					int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt == 0) {
						JObjetos.JReports.mostrarReporteParametros("fichajes\\fichajesPorMesAnioEmpleado.jasper",
								hashMap);
					} else if (opt == 1) {

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRutaParametros(
									"fichajes\\fichajesPorMesAnioEmpleado.jasper", rutaYNombre[0], hashMap);

						}
					} else if (codigo == 4) {

					}
				} else if (codigo == 4) {
					String debolucion1 = CBEmpleado.getSelectedItem().toString();
					String[] partes1 = debolucion1.split("-");
					hashMap.put("mes", monthChooser.getMonth() + 1);
					hashMap.put("anio", Integer.parseInt(CBAnios.getSelectedItem().toString()));
					int opt1 = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt1 == 0) {
						JObjetos.JReports.mostrarReporteParametros("fichajes\\fichajesMensual.jasper", hashMap);
					} else if (opt1 == 1) {

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRutaParametros("fichajes\\fichajesMensual.jasper",
									rutaYNombre[0], hashMap);

						}
					} else {

					}
				}
			}
		});
		btnNewButton.setBounds(236, 222, 141, 41);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("SELECTOR DE MES Y A\u00D1O");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 61, 397, 41);
		contentPanel.add(lblNewLabel_2);
		if (codigo == 1) {
			CBEmpleado.setVisible(false);
			lblNewLabel_1_1_1.setVisible(false);
			monthChooser.setVisible(false);
			lblNewLabel_1.setVisible(false);
			lblNewLabel_2.setText("Infrome anual general");

		} else if (codigo == 2) {
			lblNewLabel_2.setText("Infrome mensual por empleado");

		} else if (codigo == 3) {
			lblNewLabel_2.setText("Infrome anual por empleado");
			monthChooser.setVisible(false);
			lblNewLabel_1.setVisible(false);
		}else if(codigo==4) {
			lblNewLabel_2.setText("Infrome mensual");

			CBEmpleado.setVisible(false);
			lblNewLabel_1_1_1.setVisible(false);
		}
	}
}
