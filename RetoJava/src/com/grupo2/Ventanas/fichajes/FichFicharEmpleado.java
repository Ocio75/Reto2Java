package com.grupo2.Ventanas.fichajes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.DAO_fichajes;
import com.grupo2.BBDD.Modelo_DTO.DTO_fichaje;
import com.grupo2.Utiles.MensaEmergentes;
import com.grupo2.Ventanas.departamentos.DepAltaMod;
import javax.swing.JLabel;
import java.awt.Font;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class FichFicharEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTimeChooser horaEntrada;
	private JDateChooser dateChooser;
	private JLabel lblNewLabel_4;
	private int codigoFich;
	private JTable table;
	public FichFicharEmpleado(int dni) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/fichar.png")));
		setTitle("Fichages");
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel_4 = new JLabel("Hora entrada");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(57, 73, 88, 21);
		contentPanel.add(lblNewLabel_4);
		
		horaEntrada = new JTimeChooser();
		horaEntrada.getTimeField().setText("00:00");
		horaEntrada.getTimeField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		horaEntrada.setBorder(null);
		horaEntrada.setBounds(57, 104, 69, 35);
		contentPanel.add(horaEntrada);
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.setDateFormatString("dd/MM/yy");
		dateChooser.setBounds(47, 200, 113, 35);
		contentPanel.add(dateChooser);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(47, 169, 88, 21);
		contentPanel.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 47, 325, 386);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		dateChooser.setDate(new java.util.Date());

		JButton btnNewButton = new JButton("Fichar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO_fichajes fichajes = new DAO_fichajes();

				if(lblNewLabel_4.getText().equals("Hora entrada")) {
					if(fichajes.insertar(generarObjeto(dni))) {
						MensaEmergentes.alerta(1,"Fichaje registrado ", "Informacion");
						comprovatFichaje(dni);
						cargarTabla(dni);
					}else {
						MensaEmergentes.alerta(4,"No se a podido registrar el fichaje ", "Informacion");

					}
				}else if(lblNewLabel_4.getText().equals("Hora salida")) {
					DTO_fichaje fichaje = fichajes.buscar(codigoFich);
					fichaje.setHora_salida(new Time(horaEntrada.getHours(),horaEntrada.getMinutes(),0));
					if(fichajes.actualizar(fichaje)) {
						MensaEmergentes.alerta(1,fichaje.toString(), "Informacion");

						MensaEmergentes.alerta(1,"Fichaje cerrado ", "Informacion");
						comprovatFichaje(dni);
						cargarTabla(dni);
					}else {
						MensaEmergentes.alerta(4,"No se a podido cerrar el fichaje ", "Informacion");

					}
				}
			}
		});
		btnNewButton.setBounds(60, 285, 113, 40);
		contentPanel.add(btnNewButton);
		comprovatFichaje(dni);
		cargarTabla(dni);
		Calendar calendar = Calendar.getInstance();
		java.util.Date fecha = calendar.getTime();
		Time horaActual = new Time(fecha.getTime());

		horaEntrada.setTime(horaActual);
	}
	private DTO_fichaje generarObjeto(int dni) {
	
		Time hEntrada = new Time(horaEntrada.getHours(), horaEntrada.getMinutes(), 0);
		Date fecha = null;
		if (dateChooser.getDate() != null) {
			fecha = new Date(dateChooser.getDate().getTime());
		}
		return new DTO_fichaje(codigoFich,dni, hEntrada,new Time(0,0,0), fecha);
	}
	private void cargarTabla(int dni) {
	    DAO_fichajes fichajes = new DAO_fichajes();
	    fichajes.cargarTablaPorEmpleadoYFecha(table, dni, new Date(System.currentTimeMillis()));
	}

	private void comprovatFichaje(int dni) {
		DAO_fichajes fichajes = new DAO_fichajes();
		codigoFich=fichajes.fichagesAbiertos(dni);
		if(codigoFich==-1) {
			lblNewLabel_4.setText("Hora entrada");
		}else {
			lblNewLabel_4.setText("Hora salida");
			
		}
		
	}
}
