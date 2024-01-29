package com.grupo2.Ventanas.fichajes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.grupo2.BBDD.Modelo_DAO.DAO_departamento;
import com.grupo2.BBDD.Modelo_DAO.*;
import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.BBDD.Modelo_DTO.DTO_fichaje;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;
import com.grupo2.Ventanas.departamentos.DepAltaMod;
import com.toedter.components.JSpinField;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import lu.tudor.santec.jtimechooser.TimeChooserHandler;
import lu.tudor.santec.jtimechooser.TimeChooserModel;
import com.raven.swing.TimePicker;
import com.raven.swing.TimePickerLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FichFicharGeneral extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTimeChooser horaSalida;
	private JTimeChooser horaEntrada;
	private JDateChooser dateChooser;
	private JTable table;
	private JTextField txt_nombre;
	private JTextField txt_puesto;
	private JComboBox comboBox;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	public FichFicharGeneral() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/fichar.png")));

		setTitle("Gestion Fichages");
		setMinimumSize(new Dimension(800, 600));
		setSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("FICHAJES ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-27, 0, 786, 60);
		contentPanel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DAO_departamento departamento = new DAO_departamento();
				DAO_fichajes fichajes = new DAO_fichajes();
				DAO_empleados empleados = new DAO_empleados();

				String debolucion = comboBox.getSelectedItem().toString();
				String[] partes = debolucion.split("-");
				String dni = partes[0].trim();
				String nombreEmp = partes[1].trim();
				fichajes.cargarTablaPorEmpleado(table, Integer.parseInt(dni));
				txt_nombre.setText(nombreEmp);
				txt_puesto.setText(departamento.buscar(empleados.buscar(Integer.parseInt(dni)).getCodigo_departamento())
						.getNombre());
			}
		});
		comboBox.setBounds(54, 144, 172, 49);
		contentPanel.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 92, 327, 446);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txt_nombre = new JTextField();
		txt_nombre.setBounds(54, 224, 172, 49);
		contentPanel.add(txt_nombre);
		txt_nombre.setColumns(10);
		txt_nombre.setEditable(false);

		txt_puesto = new JTextField();
		txt_puesto.setColumns(10);
		txt_puesto.setBounds(54, 310, 172, 49);
		contentPanel.add(txt_puesto);
		txt_puesto.setEditable(false);

		lblNewLabel_1 = new JLabel("Empleados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(54, 123, 88, 21);
		contentPanel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(54, 203, 88, 21);
		contentPanel.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Puesto");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(54, 289, 88, 21);
		contentPanel.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Hora entrada");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(259, 137, 88, 21);
		contentPanel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Hora salida");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(259, 217, 88, 21);
		contentPanel.add(lblNewLabel_5);

		horaEntrada = new JTimeChooser();
		horaEntrada.setBorder(null);
		horaEntrada.getTimeField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		horaEntrada.getTimeField().setText("00:00");
		horaEntrada.setBounds(269, 158, 69, 35);
		contentPanel.add(horaEntrada);

		horaSalida = new JTimeChooser();
		horaSalida.getTimeField().setText("00:00");
		horaSalida.getTimeField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		horaSalida.setBorder(null);
		horaSalida.setBounds(269, 240, 69, 35);
		contentPanel.add(horaSalida);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateChooser.setDateFormatString("dd/MM/yy");
		dateChooser.setBounds(259, 324, 113, 35);
		contentPanel.add(dateChooser);
		dateChooser.setDate(new java.util.Date());

		lblNewLabel_6 = new JLabel("Fecha");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(259, 293, 88, 21);
		contentPanel.add(lblNewLabel_6);

		JButton btnNewButton = new JButton("FICHAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					DAO_fichajes fichajes = new DAO_fichajes();
					if (fichajes.insertar(generarObjeto())) {
						String debolucion = comboBox.getSelectedItem().toString();
						String[] partes = debolucion.split("-");
						String dni = partes[0].trim();
						Calendar calendar = Calendar.getInstance();
						java.util.Date fecha = calendar.getTime();
						Time horaActual = new Time(fecha.getTime());
						fichajes.cargarTablaPorEmpleado(table, Integer.parseInt(dni));
						horaEntrada.setTime(horaActual);
						MensaEmergentes.alerta(1, "Fichaje añadido con exito ", "Informacion");

					}else {
						MensaEmergentes.alerta(4, "No se a podido realizar el fichaje", "error");

					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(65, 406, 134, 42);
		contentPanel.add(btnNewButton);
		Calendar calendar = Calendar.getInstance();
		java.util.Date fecha = calendar.getTime();
		Time horaActual = new Time(fecha.getTime());

		horaEntrada.setTime(horaActual);

		cargarDatos();
	}

	private void limpiarCampos() {
	}

	private boolean validarCampos() {
		Time hEntrada = new Time(horaEntrada.getHours(), horaEntrada.getMinutes(), 0);
		Time hSalida = new Time(horaSalida.getHours(), horaSalida.getMinutes(), 0);

		if (hEntrada.before(hSalida)) {
			return true;
		} else {
			MensaEmergentes.alerta(4, " La hora de entrada debe ser menor que la de salida.", "error");
			return false;
		}
	}

	private DTO_fichaje generarObjeto() {
		String debolucion = comboBox.getSelectedItem().toString();
		String[] partes = debolucion.split("-");
		String dni = partes[0].trim();
		Time hEntrada = new Time(horaEntrada.getHours(), horaEntrada.getMinutes(), 0);
		Time hSalida = new Time(horaSalida.getHours(), horaSalida.getMinutes(), 0);
		Date fecha = null;
		if (dateChooser.getDate() != null) {
			fecha = new Date(dateChooser.getDate().getTime());
		}
		return new DTO_fichaje(Integer.parseInt(dni), hEntrada, hSalida, fecha);
	}

	private void cargarDatos() {
		DAO_empleados empleados = new DAO_empleados();

		ArrayList<DTO_empleados> listaEmpleados = empleados.listarTodos();
		String[] datosEmpleados = new String[listaEmpleados.size()];
		int i = 0;
		for (DTO_empleados empleado : listaEmpleados) {
			datosEmpleados[i] = empleado.getDni() + "-" + empleado.getNombre();
			i++;
		}
		JObjetos.comboBox.llenarCombo(datosEmpleados, comboBox);
	}
}
