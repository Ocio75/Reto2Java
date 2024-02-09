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
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel Menu_Fichages;
	private JPanel Menu_Empleados;
	private JPanel Menu_Departamentos;
	private JPanel Menu_Informes;
	public Menu_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_fichajes = new JButton("");
		btn_fichajes.setBackground(new Color(128, 128, 128));
		btn_fichajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(true);
				Menu_Empleados.setVisible(false);
				Menu_Informes.setVisible(false);
				Menu_Departamentos.setVisible(false);
			}
		});
		btn_fichajes.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/fichajes.png")));
		btn_fichajes.setBounds(236, 125, 162, 150);
		contentPane.add(btn_fichajes);
		
		JLabel Lbl_encabezado = new JLabel("Gestion bar");
		Lbl_encabezado.setFont(new Font("Tahoma", Font.BOLD, 50));
		Lbl_encabezado.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_encabezado.setBounds(125, 0, 679, 102);
		contentPane.add(Lbl_encabezado);
		
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
		
		JButton Btn_AltaEmp = new JButton("Alta");
		Btn_AltaEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpAltaMod ventan = new EmpAltaMod();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
				
			}
		});
		Btn_AltaEmp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_AltaEmp.setBounds(28, 47, 141, 44);
		Menu_Empleados.add(Btn_AltaEmp);
		
		JButton Btn_ModEmp = new JButton("Modificar");
		Btn_ModEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpAltaMod ventan = new EmpAltaMod(0);
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ModEmp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_ModEmp.setBounds(28, 101, 141, 44);
		Menu_Empleados.add(Btn_ModEmp);
		
		JLabel Lbl_EncabezadoEmp = new JLabel("Menu empleados");
		Lbl_EncabezadoEmp.setFont(new Font("Tahoma", Font.BOLD, 18));
		Lbl_EncabezadoEmp.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_EncabezadoEmp.setBounds(0, 0, 200, 37);
		Menu_Empleados.add(Lbl_EncabezadoEmp);
		
		JButton Btn_BajaEmp = new JButton("Baja");
		Btn_BajaEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpBaja ventan = new EmpBaja();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_BajaEmp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_BajaEmp.setBounds(28, 156, 141, 44);
		Menu_Empleados.add(Btn_BajaEmp);
		
		JButton Btn_ConsultaEmp = new JButton("Visualizar");
		Btn_ConsultaEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpVisualizar ventan = new EmpVisualizar();
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ConsultaEmp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_ConsultaEmp.setBounds(28, 210, 141, 44);
		Menu_Empleados.add(Btn_ConsultaEmp);
		
		Menu_Fichages = new JPanel();
		Menu_Fichages.setLayout(null);
		Menu_Fichages.setVisible(false);
		Menu_Fichages.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_Fichages.setBounds(0, 0, 200, 369);
		Menu_lateral.add(Menu_Fichages);
		
		JButton Btn_fichGeneral = new JButton("Gestion Fichajes");
		Btn_fichGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichFicharGeneral ventan = new FichFicharGeneral();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_fichGeneral.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_fichGeneral.setBounds(28, 47, 151, 44);
		Menu_Fichages.add(Btn_fichGeneral);
		
		JButton Btn_fichEmpl = new JButton("Fichar Empleado");
		Btn_fichEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventan = new Login();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_fichEmpl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_fichEmpl.setBounds(28, 101, 151, 44);
		Menu_Fichages.add(Btn_fichEmpl);
		
		JLabel Lbl_EncabezadoFich = new JLabel("Menu fichajes");
		Lbl_EncabezadoFich.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_EncabezadoFich.setFont(new Font("Tahoma", Font.BOLD, 18));
		Lbl_EncabezadoFich.setBounds(0, 0, 200, 37);
		Menu_Fichages.add(Lbl_EncabezadoFich);
		
		JButton btn_departamentos = new JButton("");
		btn_departamentos.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/departamentos.png")));
		btn_departamentos.setBackground(new Color(128, 128, 128));
		btn_departamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(false);
				Menu_Informes.setVisible(false);
				Menu_Departamentos.setVisible(true);


			}
		});
		btn_departamentos.setBounds(428, 125, 162, 150);
		contentPane.add(btn_departamentos);
		
		JButton btn_Empleados = new JButton("");
		btn_Empleados.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/empleados.png")));
		btn_Empleados.setBackground(new Color(128, 128, 128));
		btn_Empleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(true);
				Menu_Informes.setVisible(false);
				Menu_Departamentos.setVisible(false);

			}
		});
		btn_Empleados.setBounds(236, 294, 162, 147);
		contentPane.add(btn_Empleados);
		
		JButton btn_Informes = new JButton("");
		btn_Informes.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/informes.png")));
		btn_Informes.setBackground(new Color(128, 128, 128));
		btn_Informes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Fichages.setVisible(false);
				Menu_Empleados.setVisible(false);
				Menu_Informes.setVisible(true);
				Menu_Departamentos.setVisible(false);
			}
		});
		btn_Informes.setBounds(428, 294, 162, 147);
		contentPane.add(btn_Informes);
		Menu_Departamentos = new JPanel();
		Menu_Departamentos.setVisible(false);
		Menu_Departamentos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_Departamentos.setBounds(0, 0, 200, 369);
		Menu_lateral.add(Menu_Departamentos);
		Menu_Departamentos.setLayout(null);
		
		JLabel Lbl_EncabezadoDep = new JLabel("Menu Departamentos");
		Lbl_EncabezadoDep.setBounds(0, 0, 200, 37);
		Lbl_EncabezadoDep.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_EncabezadoDep.setFont(new Font("Tahoma", Font.BOLD, 16));
		Menu_Departamentos.add(Lbl_EncabezadoDep);
		
		JButton Btn_AltaDep = new JButton("Alta");
		Btn_AltaDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepAltaMod ventan = new DepAltaMod();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_AltaDep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_AltaDep.setBounds(28, 47, 141, 44);
		Menu_Departamentos.add(Btn_AltaDep);
		
		JButton Btn_ModDep = new JButton("Modificar");
		Btn_ModDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepAltaMod ventan = new DepAltaMod(0);
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ModDep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_ModDep.setBounds(28, 101, 141, 44);
		Menu_Departamentos.add(Btn_ModDep);
		
		JButton Btn_BajaDep = new JButton("Baja");
		Btn_BajaDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepBaja ventan = new DepBaja();
				ventan.setResizable(false);
				ventan.setModal(true);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_BajaDep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_BajaDep.setBounds(28, 156, 141, 44);

		Menu_Departamentos.add(Btn_BajaDep);
		
		JButton Btn_ConsultaDep = new JButton("Visualizar");
		Btn_ConsultaDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepVisualizar ventan = new DepVisualizar();
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ConsultaDep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Btn_ConsultaDep.setBounds(28, 210, 141, 44);
		Menu_Departamentos.add(Btn_ConsultaDep);
		
		Menu_Informes = new JPanel();
		Menu_Informes.setLayout(null);
		Menu_Informes.setVisible(false);
		Menu_Informes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Menu_Informes.setBounds(0, 0, 200, 369);
		Menu_lateral.add(Menu_Informes);
		
		JLabel Lbl_EncabezadoInfor = new JLabel("Informess");
		Lbl_EncabezadoInfor.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_EncabezadoInfor.setFont(new Font("Tahoma", Font.BOLD, 16));
		Lbl_EncabezadoInfor.setBounds(0, 0, 200, 37);
		Menu_Informes.add(Lbl_EncabezadoInfor);
		
		JButton Btn_AltaDep_1 = new JButton("Informe anual ");
		Btn_AltaDep_1.setActionCommand("Informe anual ");
		Btn_AltaDep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateMesAnio ventan = new DateMesAnio(1);
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_AltaDep_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Btn_AltaDep_1.setBounds(28, 47, 141, 44);
		Menu_Informes.add(Btn_AltaDep_1);
		
		JButton Btn_ModDep_1 = new JButton("Informe mensual E");
		Btn_ModDep_1.setActionCommand("Informe mensual \r\npor empleado");
		Btn_ModDep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateMesAnio ventan = new DateMesAnio(2);
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ModDep_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Btn_ModDep_1.setBounds(28, 156, 141, 44);
		Menu_Informes.add(Btn_ModDep_1);
		
		JButton Btn_BajaDep_1 = new JButton("Informe mensual");
		Btn_BajaDep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateMesAnio ventan = new DateMesAnio(4);
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_BajaDep_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Btn_BajaDep_1.setBounds(26, 101, 141, 44);
		Menu_Informes.add(Btn_BajaDep_1);
		
		JButton Btn_ConsultaDep_1 = new JButton("Informe anual E");
		Btn_ConsultaDep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateMesAnio ventan = new DateMesAnio(3);
				ventan.setResizable(false);
				ventan.setModal(false);
				ventan.setLocationRelativeTo(null);
				ventan.setVisible(true);
			}
		});
		Btn_ConsultaDep_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Btn_ConsultaDep_1.setBounds(26, 210, 141, 44);
		Menu_Informes.add(Btn_ConsultaDep_1);
		
		JButton btn_fichajes_1 = new JButton("");
		btn_fichajes_1.setIcon(new ImageIcon(Menu_principal.class.getResource("/Botones/terraza.png")));
		btn_fichajes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RMIVentana ventan;
				try {
					ventan = new RMIVentana();
					ventan.setResizable(false);
					ventan.setModal(false);
					ventan.setLocationRelativeTo(null);
					ventan.setVisible(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btn_fichajes_1.setBackground(Color.GRAY);
		btn_fichajes_1.setBounds(618, 125, 162, 150);
		contentPane.add(btn_fichajes_1);
		Menu_Fichages.setVisible(false);
		Menu_Empleados.setVisible(false);
		Menu_Informes.setVisible(false);
		Menu_Departamentos.setVisible(false);


	}
}
