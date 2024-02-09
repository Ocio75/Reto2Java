package com.grupo2.Utiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.grupo2.Interfaces.interfazServ;

public class cargaTxt implements interfazServ, Serializable {

	public cargaTxt() {

	}

	@Override
	public String tiempo(String url) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date fecha = calendar.getTime();
		String archivo = new SimpleDateFormat("ddMMyyyy").format(fecha) + "clima.txt";

		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();

			String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();

			InputStream is = uc.getInputStream();
			BufferedInputStream in = new BufferedInputStream(is);

			String fileName = archivo;

			System.out.println("Descargando el contenido de " + url);

			File outputFile = new File("D:/descargas/" + fileName);

			FileOutputStream fos = new FileOutputStream(outputFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			in.close();

			System.out.println("Descarga completada. El contenido se ha guardado en: " + outputFile.getAbsolutePath());

			if (outputFile.length() > 0) {
			} else {
				System.out.println("El archivo descargado tiene un tamaño de 0.");
			}
		} catch (MalformedURLException e) {
			System.out.println("URL mal formada: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de entrada/salida: " + e.getMessage());
			e.printStackTrace();
		}

		String rutaArchivo = "D:/descargas/" + archivo;

		String maniana = "";
		String tarde = "";
		String noche = "";

		String franjaManiana = "";
		String franjaTarde = "";
		String franjaNoche = "";
		try {
			FileReader fileReader = new FileReader(rutaArchivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			bufferedReader.readLine();
			bufferedReader.readLine();

			maniana = bufferedReader.readLine();
			tarde = bufferedReader.readLine();
			noche = bufferedReader.readLine();

			franjaManiana = obtenerHoraInicio(maniana);
			franjaTarde = obtenerHoraInicio(tarde);
			franjaNoche = obtenerHoraInicio(noche);

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SimpleDateFormat formatoHora = new SimpleDateFormat("HH");
		String horaActual = formatoHora.format(new Date());

		// Comprobar el periodo del dia, para despues tomar esos datos

		boolean enFranjaManiana = verificarHoraEnFranja(horaActual, franjaManiana, "12");
		boolean enFranjaTarde = verificarHoraEnFranja(horaActual, franjaTarde, "18");
		boolean enFranjaNoche = verificarHoraEnFranja(horaActual, franjaNoche, "24");

		// Interpretacion de la

		String franjaActiva = determinarFranjaActiva(enFranjaManiana, enFranjaTarde, enFranjaNoche);
		System.out.println(" ");
		switch (franjaActiva) {
		case "Mañana":

			return recomendaciones(maniana);
		case "Tarde":

			return recomendaciones(tarde);
		case "Noche":
			return recomendaciones(noche);

		default:
			return "";
		}

	}

	// Despejado,Nuboso,Cubierto,Nubes
	// Altas,Lluvia,Nieve,Tormenta,Niebla,Bruma,Calima

	private static String recomendacionTerraza(String cadena, int temp, int windSpeed) {
		Map<String, Boolean> condicionesMap = new HashMap<>();
		// Buscamos los valores que pueda tener el clima, para hacer recomendaciones en
		// base a eso.
		condicionesMap.put("Calima", false);
		condicionesMap.put("Bruma", false);
		condicionesMap.put("Niebla", false);

		condicionesMap.put("Escasa", false);
		condicionesMap.put("Nieve", false);
		condicionesMap.put("Tormenta", false);
		condicionesMap.put("Lluvia", false);

		condicionesMap.put("Cubierto", false);

		condicionesMap.put("Nuboso", false);
		condicionesMap.put("Muy", false);
		condicionesMap.put("Poco", false);

		condicionesMap.put("Nubes", false);

		condicionesMap.put("Despejado", false);

		for (String condicion : condicionesMap.keySet()) {
			if (cadena.toLowerCase().contains(condicion.toLowerCase())) {
				condicionesMap.put(condicion, true);
			}
		}

		// TEMPERATURA
		String recomendacion = " además, va a hacer";
		if (temp <= 10) {
			recomendacion += " fresco, por lo que se recomienda encender la estufa";
		} else if (temp > 10 && temp <= 25) {
			recomendacion += " una temperatura moderada";
		} else {
			recomendacion += " bastante calor, enciende el ventilador y los aspersores";
		}

		// VIENTO
		if (windSpeed <= 20) {
			recomendacion += " ,adicionalmente, deja el cortavientos bajado.";

		} else if (windSpeed > 20) {
			recomendacion += " ,adicionalmente, sube el cortavientos.";
		}

		// CLIMA
		String mensaje = "El clima de hoy vendrá";
		mensaje += "";

		for (Map.Entry<String, Boolean> entry : condicionesMap.entrySet()) {
			if (entry.getValue()) {
				switch (entry.getKey()) {
				case "Despejado":
					mensaje += " despejado";
					break;

				case "Nubes":
					if (condicionesMap.get("Muy")) {
						mensaje += " con bastante nubes en el cielo, ";
					}
					if (condicionesMap.get("Poco")) {
						mensaje += " con muy pocas nubes en el cielo";
					}
					mensaje += " con pocas nubes en el cielo ";
					break;

				case "Nuboso":
					mensaje += " con el cielo lleno de nubes,";
					break;

				case "Cubierto":
					mensaje += " cubierto de nubes,";
					break;

				case "Niebla":
					mensaje += " con niebla";
					break;

				case "Bruma":
					mensaje += " con bruma";
					break;

				case "Calima":
					mensaje += " con tormenta de arena";
					break;

				}
			}
		}

		if (!condicionesMap.get("Tormenta")) {
			if (condicionesMap.get("Escasa")) {
				mensaje += " y escasa lluvia, abre el toldo por si acaso";

			} else if (condicionesMap.get("Lluvia")) {
				mensaje += " y lluvia, abre el toldo";

			}

		} else {
			if (condicionesMap.get("Escasa")) {
				mensaje += " y tormenta con probabilidad de lluvia, abre el toldo, y asegurate de que no empeora, en caso de que lo haga, cierra el toldo y la terraza";

			}

			else if (condicionesMap.get("Lluvia")) {
				mensaje += " y tormenta con lluvia, abre el toldo, y asegurate de que no empeora, en caso de que lo haga, cierra el toldo y la terraza";
			}
		}
		if (condicionesMap.get("Nieve")) {
			mensaje += " y una nevada, cierra la terraza y atiende dentro.";
		} else if (condicionesMap.get("Escasa") && condicionesMap.get("Nieve")) {
			mensaje += " y  probabilidad de una nevada, pon el toldo, y asegurate de que no nieve mucho, en caso de que lo haga, cierra el toldo la terraza";
		}

		if (!condicionesMap.get("Nieve")) {
			mensaje += recomendacion;
		}
		mensaje+="\n\nDatos:\n Clima: "+cadena+"\n Temperatura:"+temp+"\n VelocidadV:"+ windSpeed;
		return mensaje;

	}

	private static String recomendaciones(String periodo) {
		if (periodo != null && !periodo.isEmpty()) {
			String trimmedPeriodo = periodo.trim();

			String[] valoresDerecha = trimmedPeriodo.split(":");

			if (valoresDerecha.length >= 2) {
				String valores = valoresDerecha[1].trim();

				String[] valoresSeparados = valores.split(",");

				if (valoresSeparados.length >= 2) {

					String clima = valoresSeparados[0].trim();
					String temperatura = valoresSeparados[1].trim();
					String velocidadV = valoresSeparados[2].trim();

					System.out.println("Clima: " + clima);
					System.out.println("Temperatura: " + temperatura);
					System.out.println("VelocidadV: " + velocidadV);

					int temp = Integer.parseInt(temperatura);
					int vel = Integer.parseInt(velocidadV);

					//
					return (recomendacionTerraza(clima, temp, vel));

				} else {
					System.out.println("Error: No se encontraron suficientes valores después de dividir por coma.");
				}
			} else {
				System.out
						.println("Error: No se encontraron suficientes valores después de dividir por los dos puntos.");
			}
		} else {
			System.out.println("Error: El periodo no tiene valor.");
		}
		return "";
	}

	private static String determinarFranjaActiva(boolean enFranjaManiana, boolean enFranjaTarde,
			boolean enFranjaNoche) {
		if (enFranjaManiana) {
			return "Mañana";
		} else if (enFranjaTarde) {
			return "Tarde";
		} else if (enFranjaNoche) {
			return "Noche";
		} else {
			return "Desconocido";
		}
	}

	private static String obtenerHoraInicio(String input) {
		String[] partes = input.split(":");
		if (partes.length > 0) {
			String[] subPartes = partes[0].trim().split("-");
			if (subPartes.length > 0) {
				return subPartes[0].trim();
			}
		}
		return "0";
	}

	private static boolean verificarHoraEnFranja(String horaActual, String horaInicio, String horaFin) {

		int actual = Integer.parseInt(horaActual);
		int inicio = Integer.parseInt(horaInicio);
		int fin = Integer.parseInt(horaFin);

		return actual >= inicio && actual < fin;
	}
}