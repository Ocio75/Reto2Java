
package com.grupo2.Utiles;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.grupo2.BBDD.conexion.Conexion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.*;

public class JObjetos {
	public class JReports {
		public static void mostrarReporte(String archivo) {
			JasperPrint jasperPrintWindow = null;
			Conexion conexion = Conexion.getInstancia();
			try {
				jasperPrintWindow = JasperFillManager.fillReport("src\\com\\grupo2\\Informes\\" + archivo, null,
						conexion.getCon());
			} catch (JRException e) {
				e.printStackTrace();
			}
			JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
			jasperViewer.setVisible(true);

		}

		public static void mostrarReporteParametros(String archivo,  HashMap<String, Object> parametro) {
			JasperPrint jasperPrintWindow = null;
			Conexion conexion = Conexion.getInstancia();
			try {
				jasperPrintWindow = JasperFillManager.fillReport("src\\com\\grupo2\\Informes\\" + archivo, parametro,
						conexion.getCon());
			} catch (JRException e) {
				e.printStackTrace();
			}
			JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
			jasperViewer.setVisible(true);

		}

		public static void guardarArchivoRuta(String archivo, String ruta) {
			Conexion conexion = Conexion.getInstancia();
			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport("src\\com\\grupo2\\Informes\\" + archivo, null,
						conexion.getCon());
			} catch (JRException e1) {
				e1.printStackTrace();
			}
			JRPdfExporter exp = new JRPdfExporter();
			exp.setExporterInput(new SimpleExporterInput(jasperPrint));
			exp.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta));
			MensaEmergentes.alerta(1, "Informe generado y almacenado correctamente", "Informacion");
			try {
				exp.exportReport();
			} catch (JRException e1) {
				e1.printStackTrace();
			}

		}
	}

	public class tabla {
		public static void aumentarTamanoFuenteTabla(JTable tabla, int tamano) {
			Font font = tabla.getFont().deriveFont(Font.PLAIN, tamano);
			JTableHeader header = tabla.getTableHeader();
			tabla.setFont(font);
			header.setFont(font);
		}

		public static void establecerAnchoColumna(JTable table, int columna, int ancho) {
			TableColumnModel columnModel = table.getColumnModel();
			TableColumn column = columnModel.getColumn(columna);
			column.setMinWidth(ancho);
			column.setMaxWidth(ancho);
			column.setPreferredWidth(ancho);
		}

		public static void establecerAnchoCeroColumna(JTable table, int columna) {
			establecerAnchoColumna(table, columna, 0);
		}

	}
	 public class imagenes {

	        public static byte[] convertirImagenABytes(String rutaArchivo) {
	            try (FileInputStream fis = new FileInputStream(rutaArchivo); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

	                byte[] buffer = new byte[4096];
	                int bytesRead;
	                while ((bytesRead = fis.read(buffer)) != -1) {
	                    baos.write(buffer, 0, bytesRead);
	                }

	                return baos.toByteArray();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	            return null; // En caso de error, se retorna null
	        }

	        public static byte[] convertirImagenABytes(Icon icon) {
	            if (icon == null) {
	                return null;
	            }

	            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	            Graphics2D graphics = bufferedImage.createGraphics();
	            icon.paintIcon(null, graphics, 0, 0);
	            graphics.dispose();

	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            try {
	                ImageIO.write(bufferedImage, "png", baos);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }

	            return baos.toByteArray();
	        }
	    }
	public class comboBox {
		public static void llenarCombo(String[] valores, JComboBox combo, String frase) {
			combo.removeAllItems();
			combo.addItem(frase);
			for (String valor : valores) {
				combo.addItem(valor);
			}
		}

		public static void llenarCombo(String[] valores, JComboBox combo) {
			combo.removeAllItems();
			for (String valor : valores) {
				combo.addItem(valor);
			}
		}

		public static void selecCombo(JComboBox comboBox, String valorBuscado) {
			for (int i = 0; i < comboBox.getItemCount(); i++) {
				String item = comboBox.getItemAt(i).toString();
				String[] partes = item.split("-");
				String codigo = partes[0].trim();
				if (item != null && codigo.equals(valorBuscado)) {
					comboBox.setSelectedIndex(i);
					return;
				}
			}
		}
	}

}
