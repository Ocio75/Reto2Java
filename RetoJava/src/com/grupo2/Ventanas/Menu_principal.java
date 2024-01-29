package com.grupo2.Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import com.grupo2.Ventanas.*;
import com.grupo2.Ventanas.departamentos.*;
import com.grupo2.Ventanas.empleados.*;
import com.grupo2.Ventanas.fichajes.*;
import com.grupo2.Ventanas.otros.*;



import com.grupo2.Ventanas.otros.Login;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel Menu_Fichages;
	private JPanel Menu_Empleados;
	public Menu_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_fichajes = new JButton("");
		btn_fichajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(true);
				Menu_Empleados.setVisible(false);
			}
		});
		btn_fichajes.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/fichajes.png")));
		btn_fichajes.setBounds(276, 123, 162, 150);
		contentPane.add(btn_fichajes);
		
		JLabel lblNewLabel = new JLabel("Gestion bar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(122, 0, 619, 102);
		contentPane.add(lblNewLabel);
		
		JPanel Menu_lateral = new JPanel();
		Menu_lateral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_lateral.setBounds(0, 103, 200, 369);
		contentPane.add(Menu_lateral);
		Menu_lateral.setLayout(null);
		
		 Menu_Empleados = new JPanel();
		Menu_Empleados.setVisible(false);
		Menu_Empleados.setBounds(0, 0, 200, 369);
		Menu_lateral.add(Menu_Empleados);
		Menu_Empleados.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_Empleados.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Alta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpAltaMod ventan = new EmpAltaMod();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(28, 47, 141, 44);
		Menu_Empleados.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Modificar");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpAltaMod ventan = new EmpAltaMod(0);
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3_1.setBounds(28, 101, 141, 44);
		Menu_Empleados.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("Menu empleados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 200, 37);
		Menu_Empleados.add(lblNewLabel_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Baja");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpBaja ventan = new EmpBaja();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		btnNewButton_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3_1_1.setBounds(28, 156, 141, 44);
		Menu_Empleados.add(btnNewButton_3_1_1);
		
		Menu_Fichages = new JPanel();
		Menu_Fichages.setLayout(null);
		Menu_Fichages.setVisible(false);
		Menu_Fichages.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_Fichages.setBounds(0, 0, 200, 369);
		Menu_lateral.add(Menu_Fichages);
		
		JButton btnNewButton_3_2 = new JButton("Gestion Fichajes");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichFicharGeneral ventan = new FichFicharGeneral();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3_2.setBounds(28, 47, 151, 44);
		Menu_Fichages.add(btnNewButton_3_2);
		
		JButton btnNewButton_3_1_2 = new JButton("Fichar Empleado");
		btnNewButton_3_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventan = new Login();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		btnNewButton_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3_1_2.setBounds(28, 101, 151, 44);
		Menu_Fichages.add(btnNewButton_3_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Menu fichajes");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(0, 0, 200, 37);
		Menu_Fichages.add(lblNewLabel_1_1);
		
		JButton btn_departamentos = new JButton("New button");
		btn_departamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(false);
			}
		});
		btn_departamentos.setBounds(511, 134, 162, 139);
		contentPane.add(btn_departamentos);
		
		JButton btn_Empleados = new JButton("New button");
		btn_Empleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(true);
			}
		});
		btn_Empleados.setBounds(276, 300, 162, 139);
		contentPane.add(btn_Empleados);
		
		JButton btn_Informes = new JButton("New button");
		btn_Informes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(false);
			}
		});
		btn_Informes.setBounds(511, 300, 162, 139);
		contentPane.add(btn_Informes);
		
		
		Menu_Fichages.setVisible(false);
		Menu_Empleados.setVisible(false);
	}
}
