package com.grupo2.Ventanas.departamentos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DTO.DTO_departamentos;
import com.grupo2.Interfaces.Funciones_campos;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.MensaEmergentes;
import com.grupo2.BBDD.Modelo_DAO.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepAltaMod extends JDialog implements Funciones_campos<DTO_departamentos>{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNewLabel_1_1_1;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JTextArea textArea;
	private JLabel lblNewLabel ;
	public DepAltaMod(int c) {
		this();
		this.setTitle("Modificar Departamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/modificar.png")));
		lblNewLabel_1_1_1.setVisible(true);
		comboBox.setVisible(true);
		btnNewButton.setText("Modificar");
		lblNewLabel.setText("MODIFICAR DEPARTAMENTO");
		cargarCombo();

	}

	public DepAltaMod() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/a\u00F1adir.png")));
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Añadir Departamento");

		lblNewLabel = new JLabel("A\u00D1ADIR DEPARTAMENTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 626, 64);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(43, 136, 192, 42);
		contentPanel.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(!comboBox.getSelectedItem().toString().equals("Elige un departamento")) {
					DAO_departamento departamento = new DAO_departamento();
					String debolucion = comboBox.getSelectedItem().toString();
					System.out.println(debolucion);
					String[] partes = debolucion.split("-");
					String codigo = partes[0].trim();
					cargarCampos(departamento.buscar(Integer.parseInt(codigo)));

				}
			
			}
		});
		comboBox.setBounds(43, 241, 192, 42);
		contentPanel.add(comboBox);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setBounds(329, 139, 192, 144);
		contentPanel.add(textArea);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(43, 112, 113, 25);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(329, 112, 113, 25);
		contentPanel.add(lblNewLabel_1_1);

		lblNewLabel_1_1_1 = new JLabel("Departamentos");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(43, 218, 113, 25);
		contentPanel.add(lblNewLabel_1_1_1);

		btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarCampos()) {
					DAO_departamento departamento = new DAO_departamento();
					if (btnNewButton.getText().toString().equals("Añadir")) {
						if(departamento.insertar(generarObjeto())) {
							MensaEmergentes.alerta(1, "Departamento añadido con exito", "Informacion");
							limpiarCampos();
						}else {
							MensaEmergentes.alerta(4, "No se a podido añadir el departamento", "Error");
						}
					} else if (btnNewButton.getText().toString().equals("Modificar")) {
						String debolucion = comboBox.getSelectedItem().toString();
						String[] partes = debolucion.split("-");
						String codigo = partes[0].trim();
						if (departamento.actualizar(generarObjeto(Integer.parseInt(codigo)))) {
							MensaEmergentes.alerta(1, "Departamento actualizado con exito", "Informacion");
							limpiarCampos();
							cargarCombo();
						} else {
							MensaEmergentes.alerta(4, "No se a podido actualizar el departamento", "Error");
						}

					} else {

					}
				} else {
					MensaEmergentes.alerta(4, "Error hay campos de texto vacios", "Error");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(352, 332, 145, 42);
		contentPanel.add(btnNewButton);
		lblNewLabel_1_1_1.setVisible(false);
		comboBox.setVisible(false);

	}

	private void cargarCombo() {
		DAO_departamento departamento = new DAO_departamento();
		JObjetos.comboBox.llenarCombo(departamento.arrayNombres(), comboBox,"Elige un departamento");
	}

	private void limpiarCampos() {
		textArea.setText("");
		textField.setText("");		
	}

	private boolean validarCampos() {
		return textArea.getText().isEmpty() && textField.getText().isEmpty();

	}

	private void cargarCampos(DTO_departamentos t) {
		textArea.setText(t.getDescripcion());
		textField.setText(t.getNombre());		
	}

	private DTO_departamentos generarObjeto() {
		return new DTO_departamentos(textField.getText(),textArea.getText());
	}

	private DTO_departamentos generarObjeto(int codigo) {
		return new DTO_departamentos(codigo,textField.getText(),textArea.getText());
	}
}
