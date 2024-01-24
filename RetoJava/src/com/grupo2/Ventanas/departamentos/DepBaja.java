package com.grupo2.Ventanas.departamentos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.DAO_departamento;
import com.grupo2.BBDD.Modelo_DAO.DAO_empleados;
import com.grupo2.BBDD.Modelo_DTO.DTO_departamentos;
import com.grupo2.BBDD.Modelo_DTO.DTO_empleados;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DepBaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox comboBox;
	public DepBaja() {
		setTitle("Baja departamentos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepBaja.class.getResource("/iconos/Papelera.png")));
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEliminarDepartamento = new JLabel("ELIMINAR DEPARTAMENTO");
		lblEliminarDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarDepartamento.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEliminarDepartamento.setBounds(0, 0, 626, 64);
		contentPanel.add(lblEliminarDepartamento);
		
		JLabel lblNewLabel_2_1 = new JLabel("Departamentos");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(26, 106, 142, 24);
		contentPanel.add(lblNewLabel_2_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(26, 131, 210, 43);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Texto de seguridad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(26, 198, 142, 24);
		contentPanel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(26, 222, 210, 43);
		contentPanel.add(textField);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO_departamento departamento = new DAO_departamento();
				String debolucion = comboBox.getSelectedItem().toString();
				String[] partes = debolucion.split("-");
				String nombreEmp = partes[1].trim();
		
				if (textField.getText().toString().trim().equals(comboBox.getSelectedItem().toString().trim())) {
					if (MensaEmergentes.siNo(2, "Desea eliminara el departamento de: " + nombreEmp + "?",
							"ELiminar un cliente") == 0) {
						if (departamento.borrar(Integer.parseInt(partes[0].trim()))) {
							MensaEmergentes.alerta(1, "Departamento borrado con exito", "Informacion");
						} else {
							MensaEmergentes.alerta(4, "Error no se a podido eliminar el Departamento", "Error");
						}
					} else {
						MensaEmergentes.alerta(1, "Operacion cancelada", "Informacion");
					}
				} else {
					MensaEmergentes.alerta(4, "No coincide el texto del area de texto con el departamento elegido",
							"Error");
				}
				cargarDatos();
			}

		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(66, 308, 135, 43);
		contentPanel.add(btnEliminar);
		
		JTextArea txtrParaPoderDar = new JTextArea();
		txtrParaPoderDar.setWrapStyleWord(true);
		txtrParaPoderDar.setText("Para poder dar de eliminar un\ndepartamento ,hay que seleccionarlo \nen el desplegable y escribir el \nmismo texto del desplegable\nen el \u00E1rea de texto de \nla parte inferior.");
		txtrParaPoderDar.setSelectedTextColor(Color.WHITE);
		txtrParaPoderDar.setForeground(Color.BLACK);
		txtrParaPoderDar.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrParaPoderDar.setEnabled(false);
		txtrParaPoderDar.setEditable(false);
		txtrParaPoderDar.setDisabledTextColor(Color.BLACK);
		txtrParaPoderDar.setBackground(UIManager.getColor("Button.background"));
		txtrParaPoderDar.setBounds(268, 127, 329, 191);
		contentPanel.add(txtrParaPoderDar);
		cargarDatos() ;
	}
private void cargarDatos() {
	DAO_departamento departamento = new DAO_departamento();
	JObjetos.comboBox.llenarCombo(departamento.arrayNombres(), comboBox);
}
}
