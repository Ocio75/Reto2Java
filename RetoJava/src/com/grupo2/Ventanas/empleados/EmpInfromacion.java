package com.grupo2.Ventanas.empleados;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.*;

import com.grupo2.BBDD.Modelo_DTO.DTO_departamentos;
import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.Interfaces.Funciones_campos;
import com.grupo2.Utiles.Archivos;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;
import com.grupo2.Ventanas.departamentos.DepAltaMod;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpInfromacion extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_dni;
	private JTextField txt_nombre;
	private JLabel IMG_foto;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JLabel lblNewLabel_2;
	private JLabel lblApellidos;
	private JTextField txt_numeross;
	private JTextField txt_apellidos;
	private JLabel lblNewLabel_4;
	private JLabel lblContrasea;
	private JTextField txt_pasw;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JLabel lblAadirEmpleado;
	private JTable table;
	private JLabel lblNewLabel_1;
	public EmpInfromacion(int dni) {
		this();

		DAO_empleados empleado = new DAO_empleados();
		cargarCampos(empleado.buscar(dni));
		DAO_fichajes fichajes = new DAO_fichajes();
		Calendar calendar = Calendar.getInstance();
		java.util.Date fecha = calendar.getTime();
		fichajes.cargarTablaPorEmpleadoYFecha(table, dni, fecha);
	
	}

	private EmpInfromacion() {
		this.setTitle("Informacion empleado");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/a\u00F1adir.png")));

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setMinimumSize(new Dimension(800, 600));
		setSize(new Dimension(827, 648));
		setPreferredSize(new Dimension(800, 600));
		setModal(true);
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblAadirEmpleado = new JLabel("INFORMACION EMPLEADO");
		lblAadirEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblAadirEmpleado.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAadirEmpleado.setBounds(0, 0, 786, 81);
		contentPanel.add(lblAadirEmpleado);

		txt_dni = new JTextField();
		txt_dni.setEnabled(false);
		txt_dni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_dni.setBounds(66, 156, 167, 42);
		contentPanel.add(txt_dni);
		txt_dni.setColumns(10);

		txt_nombre = new JTextField();
		txt_nombre.setEnabled(false);
		txt_nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_nombre.setColumns(10);
		txt_nombre.setBounds(66, 235, 167, 42);
		contentPanel.add(txt_nombre);

		IMG_foto = new JLabel("");
		IMG_foto.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		IMG_foto.setBounds(504, 129, 218, 244);
		contentPanel.add(IMG_foto);

		lblNewLabel = new JLabel("Dni");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(67, 129, 109, 27);
		contentPanel.add(lblNewLabel);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(67, 208, 109, 27);
		contentPanel.add(lblNombre);

		lblNewLabel_2 = new JLabel("N\u00BA seguridad social");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(288, 287, 141, 27);
		contentPanel.add(lblNewLabel_2);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(67, 287, 109, 27);
		contentPanel.add(lblApellidos);

		txt_numeross = new JTextField();
		txt_numeross.setEnabled(false);
		txt_numeross.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_numeross.setColumns(10);
		txt_numeross.setBounds(287, 314, 167, 42);
		contentPanel.add(txt_numeross);

		txt_apellidos = new JTextField();
		txt_apellidos.setEnabled(false);
		txt_apellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_apellidos.setColumns(10);
		txt_apellidos.setBounds(66, 314, 167, 42);
		contentPanel.add(txt_apellidos);

		lblNewLabel_4 = new JLabel("Departamento");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(287, 208, 109, 27);
		contentPanel.add(lblNewLabel_4);

		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(287, 129, 109, 27);
		contentPanel.add(lblContrasea);

		txt_pasw = new JTextField();
		txt_pasw.setEnabled(false);
		txt_pasw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_pasw.setColumns(10);
		txt_pasw.setBounds(286, 156, 167, 42);
		contentPanel.add(txt_pasw);

		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(287, 235, 167, 42);
		contentPanel.add(comboBox);

		JLabel lblNewLabel_4_1 = new JLabel("Antiguedad");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(66, 372, 109, 27);
		contentPanel.add(lblNewLabel_4_1);

		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooser.setDateFormatString("d/MM/y");
		dateChooser.setBounds(66, 399, 167, 42);
		contentPanel.add(dateChooser);
		dateChooser.setDate(new java.util.Date());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 393, 529, 208);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		 lblNewLabel_1 = new JLabel("New label");
		 lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(66, 451, 167, 27);
		contentPanel.add(lblNewLabel_1);
		cargarDepartamentosCombo();
	}
	private String antiguedad(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date fechah = calendar.getTime();
	    long currentTime = new Date(fechah.getDate()).getTime();
	    long differenceInDays = (currentTime - fecha.getTime()) / (1000 * 60 * 60 * 24);
	    long years = differenceInDays / 365;
	    long months = (differenceInDays % 365) / 30;

	    return years + " años y " + months + " meses";
	}

	private void cargarDepartamentosCombo() {
		DAO_departamento departamento = new DAO_departamento();
		JObjetos.comboBox.llenarCombo(departamento.arrayNombres(), comboBox);
	}

	private boolean validarNumericos() {
		try {
			Long.parseLong(txt_dni.getText().trim());
			Long.parseLong(txt_numeross.getText().trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void limpiarCampos() {
		txt_dni.setText("");
		txt_nombre.setText("");
		txt_numeross.setText("");
		txt_apellidos.setText("");
		txt_pasw.setText("");
		IMG_foto.setIcon(null);
		dateChooser.setDate(new java.util.Date());
	}

	private boolean validarCampos() {
		return !txt_dni.getText().isEmpty() && !txt_nombre.getText().isEmpty() && !txt_numeross.getText().isEmpty()
				&& !txt_apellidos.getText().isEmpty() && !txt_pasw.getText().isEmpty() && validarNumericos();
	}

	private void cargarCampos(DTO_empleados t) {
		txt_dni.setText(t.getDni() + "");
		txt_nombre.setText(t.getNombre());
		txt_numeross.setText(t.getN_seguridad_social() + "");
		txt_apellidos.setText(t.getApellidos());
		txt_pasw.setText(t.getContrasena());
		JObjetos.comboBox.selecCombo(comboBox, t.getCodigo_departamento() + "");
		byte[] fotoBytes = t.getFoto();
		dateChooser.setDate(t.getAntiguedad());
		lblNewLabel_1.setText(antiguedad(t.getAntiguedad()));

		if (fotoBytes != null) {
			try {
				ImageIcon imagenIcon = new ImageIcon(fotoBytes);
				Image imagen = imagenIcon.getImage();
				int ancho = IMG_foto.getWidth();
				int alto = IMG_foto.getHeight();
				Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
				ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
				IMG_foto.setIcon(imagenRedimensionadaIcon);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private DTO_empleados generarObjeto() {
		String debolucion = comboBox.getSelectedItem().toString();
		String[] partes = debolucion.split("-");
		String dni = partes[0].trim();

		int codigo_departamento = Integer.parseInt(dni);

		Date antiguedad = null;
		if (dateChooser.getDate() != null) {
			antiguedad = new Date(dateChooser.getDate().getTime());
		}

		byte[] foto = null;
		if (IMG_foto.getIcon() != null) {
			foto = JObjetos.imagenes.convertirImagenABytes(IMG_foto.getIcon());
		}
		return new DTO_empleados(Integer.parseInt(txt_dni.getText()), txt_nombre.getText(), txt_apellidos.getText(),
				antiguedad, Integer.parseInt(txt_numeross.getText()), codigo_departamento, txt_pasw.getText(), foto);
	}
}
