package com.grupo2;
import java.util.HashMap;
import java.util.Map;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.grupo2.Ventanas.empleados.*;
import com.grupo2.Ventanas.otros.*;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.grupo2.Utiles.JObjetos;
import com.grupo2.Ventanas.Menu_principal;
import com.grupo2.Ventanas.fichajes.*;
public class Inicio {

	public static void main(String[] args) {
		FlatArcIJTheme.setup();
        //FlatArcDarkIJTheme.setup();

		Menu_principal ventan = new Menu_principal();
		ventan.setResizable(false);
		ventan.setLocationRelativeTo(null);
		ventan.setVisible(true);
		/*
		HashMap<String, Object> hashMap = new HashMap<>();
	        hashMap.put("anio", 2024);
	      
		
		JObjetos.JReports.mostrarReporteParametros("fichajes\\InfromeAnualTodosEmpleados.jasper", hashMap);*/
	}

}
