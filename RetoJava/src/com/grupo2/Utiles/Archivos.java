package com.grupo2.Utiles;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Archivos {

	public static class Files {

		public static String[] abrirFichero(String[] extensiones) {
			JFileChooser fileChooser = crearJFileChooser(extensiones, false);
			int option = fileChooser.showOpenDialog(null);

			if (option == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = fileChooser.getSelectedFile();
				String ruta = archivoSeleccionado.getAbsolutePath();
				String nombreArchivo = archivoSeleccionado.getName();
				return new String[] { ruta, nombreArchivo };
			} else {
				return null;
			}
		}

		public static String[] guardarFichero(String[] extensiones) {
			JFileChooser fileChooser = crearJFileChooser(extensiones, true);
			int option = fileChooser.showSaveDialog(null);

			if (option == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = fileChooser.getSelectedFile();
				String ruta = archivoSeleccionado.getAbsolutePath();
				String nombreArchivo = archivoSeleccionado.getName();
				return new String[] { ruta, nombreArchivo };
			} else {
				return null;
			}
		}

		public static String selectDirectory() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Seleccionar directorio");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showDialog(null, "Seleccionar");

			if (option == JFileChooser.APPROVE_OPTION) {
				return fileChooser.getSelectedFile().getAbsolutePath();
			} else {
				return null;
			}
		}

		private static JFileChooser crearJFileChooser(String[] extensiones, boolean saveMode) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(saveMode ? "Guardar archivo" : "Abrir archivo");

			// Crear filtro de extensiones permitidas
			String textExtensiones = "Archivos: .";
			textExtensiones += String.join(", .", extensiones);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(textExtensiones, extensiones);
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);

			// Establecer apariencia de Windows
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(fileChooser);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Establecer mensajes en castellano
			if (saveMode) {
				fileChooser.setApproveButtonText("Guardar");
			} else {
				fileChooser.setApproveButtonText("Abrir");
			}

			return fileChooser;
		}
	}
}
