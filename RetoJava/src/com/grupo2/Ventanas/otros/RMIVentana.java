package com.grupo2.Ventanas.otros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import com.grupo2.Utiles.*;
import com.grupo2.Interfaces.*;
import java.awt.Font;
public class RMIVentana extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	public RMIVentana()   throws RemoteException {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recomendaciones  meteorologicas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 616, 73);
		contentPanel.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 86, 606, 347);
		contentPanel.add(textArea);
		setMinimumSize(new Dimension(640, 480));
		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		 try {
			descargar();
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void descargar() throws RemoteException, MalformedURLException {
				Calendar calendar = Calendar.getInstance();
				java.util.Date fecha = calendar.getTime();
				String url = "https://timedomain.neocities.org/";
				String archivo = new SimpleDateFormat("ddMMyyyy").format(fecha) + "clima.txt";
				url+=archivo;
				System.out.println(url);
				
				cargaTxt obj = new cargaTxt();
				LocateRegistry.createRegistry(1099);
				Naming.rebind("rmi://127.0.0.1/ObjetoOperaciones", obj);
				System.out.println("Objeto operaciones registrado");
			
			 try {
				 interfazServ obj1=(interfazServ) Naming.lookup("//localhost/ObjetoOperaciones");
				 textArea.setText(obj1.tiempo(url));
      		}catch(Exception e) {
      			e.printStackTrace();
      		}

		
	}
}
