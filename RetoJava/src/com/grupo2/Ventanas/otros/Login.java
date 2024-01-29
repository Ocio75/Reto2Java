package com.grupo2.Ventanas.otros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.DAO_empleados;
import com.grupo2.Utiles.MensaEmergentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.grupo2.Ventanas.fichajes.*;
import javax.swing.JPasswordField;
public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_dni;
	private JPasswordField txt_paswd;

	public Login() {
		setBounds(100, 100, 450, 525);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" INICIO DE SESION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 436, 73);
		contentPanel.add(lblNewLabel);
		
		txt_dni = new JTextField();
		txt_dni.setBounds(120, 162, 187, 40);
		contentPanel.add(txt_dni);
		txt_dni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(120, 138, 86, 24);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(120, 226, 86, 24);
		contentPanel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO_empleados empleado = new DAO_empleados();
				if(empleado.validarCodigo(Integer.parseInt(txt_dni.getText()))) {
					if(empleado.buscar(Integer.parseInt(txt_dni.getText())).getContrasena().equals(txt_paswd.getText())) {
						FichFicharEmpleado ventan = new FichFicharEmpleado(Integer.parseInt(txt_dni.getText()));
						ventan.setResizable(false);
						ventan.setModal(true);
						ventan.setLocationRelativeTo(null);
						ventan.setVisible(true);
					}else {
						MensaEmergentes.alerta(4, "Contraseña incorrecta ", "Error");

					}
				}else {
					MensaEmergentes.alerta(4, "No existe ningun empleado ocn ese dni", "Error");

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(157, 355, 128, 40);
		contentPanel.add(btnNewButton);
		
		txt_paswd = new JPasswordField();
		txt_paswd.setBounds(120, 249, 187, 40);
		contentPanel.add(txt_paswd);
	}
}
