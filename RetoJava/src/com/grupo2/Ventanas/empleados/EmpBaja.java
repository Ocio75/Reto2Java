package com.grupo2.Ventanas.empleados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.*;
import com.grupo2.BBDD.Modelo_DTO.*;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.*;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;

public class EmpBaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox comboBox;

	public EmpBaja() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EmpBaja.class.getResource("/iconos/Papelera.png")));
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		setModal(true);
		setResizable(false);
		setTitle("Baja empleado");
		setBounds(100, 100, 577, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(45, 142, 210, 43);
		contentPanel.add(comboBox);

		JLabel lblNewLabel = new JLabel("BAJA EMPLEADO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(0, 0, 626, 81);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(45, 233, 210, 43);
		contentPanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Dar de baja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO_empleados empleados = new DAO_empleados();
				String debolucion = comboBox.getSelectedItem().toString();
				String[] partes = debolucion.split("-");
				String dni = partes[0].trim();
				String nombreEmp = partes[1].trim();
				if (textField.getText().toString().trim().equals(comboBox.getSelectedItem().toString().trim())) {
					if (MensaEmergentes.siNo(2, "Desea eliminara a " + nombreEmp + " con dni:\n" + dni + "?",
							"ELiminar un cliente") == 0) {
						if (empleados.borrar(Integer.parseInt(dni))) {
							MensaEmergentes.alerta(1, "Empleado borrado con exito", "Informacion");
						} else {
							MensaEmergentes.alerta(4, "Error no se a podido eliminar el Empleado", "Error");
						}
					} else {
						MensaEmergentes.alerta(1, "Operacion cancelada", "Informacion");
					}
				} else {
					MensaEmergentes.alerta(4, "No coincide el texto del area de texto con el empleado elegido",
							"Error");
				}
				cargarDatos();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(85, 319, 135, 43);
		contentPanel.add(btnNewButton);
        
        String texto ="Para poder dar de baja a un\nempleado," +
                "hay que seleccionarlo \nen el desplegable" +
                "y escribir el \nmismo texto del desplegable\n" +
                "en el área de texto de \nla parte inferior.";
        
        JLabel lblNewLabel_2 = new JLabel("Texto de seguridad");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(45, 209, 142, 24);
        contentPanel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Empleados");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2_1.setBounds(45, 117, 142, 24);
        contentPanel.add(lblNewLabel_2_1);
        
        JTextArea txtrParaPoderDar = new JTextArea();
        txtrParaPoderDar.setSelectedTextColor(new Color(255, 255, 255));
        txtrParaPoderDar.setForeground(new Color(0, 0, 0));
        txtrParaPoderDar.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txtrParaPoderDar.setDisabledTextColor(new Color(0, 0, 0));
        txtrParaPoderDar.setEnabled(false);
        txtrParaPoderDar.setEditable(false);
        txtrParaPoderDar.setWrapStyleWord(true);
        txtrParaPoderDar.setBackground(UIManager.getColor("CheckBox.background"));
        txtrParaPoderDar.setBounds(287, 138, 329, 191);
        txtrParaPoderDar.setText(texto);
        contentPanel.add(txtrParaPoderDar);

		cargarDatos();

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
