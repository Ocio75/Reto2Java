package com.grupo2.Ventanas.fichajes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.Ventanas.departamentos.DepAltaMod;

public class FichFicharEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public FichFicharEmpleado(int dni) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DepAltaMod.class.getResource("/iconos/fichar.png")));
		setTitle("Fichages");
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}
