package com.grupo2.Ventanas.departamentos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.grupo2.BBDD.Modelo_DAO.DAO_departamento;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Utiles.Archivos;
import com.grupo2.Utiles.MensaEmergentes;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DepVisualizar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnNewButton;

	public DepVisualizar() {
		setMinimumSize(new Dimension(854, 480));
		setTitle("Mostrar Departamentos");
		setPreferredSize(new Dimension(854, 480));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 820, 309);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		DAO_departamento departamento = new DAO_departamento();
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departamento.cargarTabla(table);

		btnNewButton = new JButton("Informe departamentos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = MensaEmergentes.previsualizarGuardad(3, "Que desea hacer con el pdf?", "Informacion");
				if (opt == 0) {
					setModal(false);
					JObjetos.JReports.mostrarReporte("departamentos\\listadoDepartamentos.jasper");
				} else if (opt == 1) {
					String[] extensiones = { "pdf" };
					String[] rutaYNombre = Archivos.Files.guardarFichero(extensiones);
					if (rutaYNombre != null) {
						JObjetos.JReports.guardarArchivoRuta("listadoDepartamentos.jasper", rutaYNombre[0]);
					}
				} else {

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(647, 81, 183, 33);
		contentPanel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("LISTADO DEPARTAMENTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 607, 114);
		contentPanel.add(lblNewLabel);
	}
}
