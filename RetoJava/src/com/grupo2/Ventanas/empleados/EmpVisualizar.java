package com.grupo2.Ventanas.empleados;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import com.grupo2.BBDD.Modelo_DAO.*;
import com.grupo2.Utiles.Archivos;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;

import java.util.HashMap;
import java.util.Map;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.grupo2.Ventanas.empleados.*;
import com.grupo2.Ventanas.otros.*;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Ventanas.Menu_principal;
import com.grupo2.Ventanas.fichajes.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpVisualizar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private int codigo=-1;
	public EmpVisualizar() {
		setModal(true);
		DAO_departamento departamento = new DAO_departamento();

		setMinimumSize(new Dimension(854, 480));
		setTitle("Mostrar Empleados");
		setPreferredSize(new Dimension(854, 480));
		setResizable(false);
		setBounds(100, 100, 854, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 143, 820, 337);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
		        String _id = String.valueOf(table.getValueAt(fila, 0));
		        codigo= Integer.parseInt(_id);
			}
		});
		scrollPane.setViewportView(table);
		DAO_empleados empleados = new DAO_empleados();
		empleados.cargarTabla(table);

	
		

		JLabel lblNewLabel = new JLabel("LISTADO EMPLEADOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(10, 10, 616, 111);
		contentPanel.add(lblNewLabel);

		btnNewButton = new JButton("Informe General");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Elige un DEPARTAMENTO")) {
					int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt == 0) {
						setModal(false);
						JObjetos.JReports.mostrarReporte("empleados\\EmpleadosPorDepartamento.jasper");
					} else if (opt == 1) {
						setModal(false);

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRuta("empleados\\EmpleadosPorDepartamento.jasper", rutaYNombre[0]);

						}
					} else {
					}
				} else {
					String debolucion = comboBox.getSelectedItem().toString();
					String[] partes = debolucion.split("-");
					String codigo = partes[0].trim();
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("dep", Integer.parseInt(codigo));
					int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
					if (opt == 0) {
						setModal(false);
						JObjetos.JReports.mostrarReporteParametros("empleados\\EmpleadosDeUnDepartamentos.jasper", hashMap);
					} else if (opt == 1) {
						setModal(false);

						String[] extensiones = { "pdf" };
						String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
						if (rutaYNombre != null) {
							JObjetos.JReports.guardarArchivoRutaParametros("empleados\\EmpleadosDeUnDepartamentos.jasper", rutaYNombre[0],hashMap);

						}
					} else {

					}
					
				}
			}
		});
		btnNewButton.setBounds(636, 55, 194, 34);
		contentPanel.add(btnNewButton);
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Elige un DEPARTAMENTO")) {
					DAO_empleados empleados = new DAO_empleados();
					empleados.cargarTabla(table);
					btnNewButton.setText("Informe General");
				} else {
					String debolucion = comboBox.getSelectedItem().toString();
					String[] partes = debolucion.split("-");
					String codigo = partes[0].trim();
					btnNewButton.setText("Informe del departamento");

					DAO_empleados empleados = new DAO_empleados();
					empleados.cargarTablaPorDep(table, Integer.parseInt(codigo));
				}
			}
		});
		comboBox.setBounds(636, 99, 194, 34);
		contentPanel.add(comboBox);
		JObjetos.comboBox.llenarCombo(departamento.arrayNombres(), comboBox, "Elige un DEPARTAMENTO");
		
		JButton btnInformacionEmpleado = new JButton("Informacion empleado");
		btnInformacionEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(codigo!=-1 ) {
					EmpInfromacion v1=new EmpInfromacion(codigo);
					v1.setLocationRelativeTo(null);
					v1.setModal(true);
					v1.setVisible(true);
				}else {
					MensaEmergentes.alerta(4, "Elige un empleado valido ", "Error");

				}
			}
		});
		btnInformacionEmpleado.setBounds(636, 10, 194, 34);
		contentPanel.add(btnInformacionEmpleado);
	}
}
