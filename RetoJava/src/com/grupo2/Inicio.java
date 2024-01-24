package com.grupo2;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.grupo2.Ventanas.Menu_principal;
import com.grupo2.Ventanas.fichajes.*;
public class Inicio {

	public static void main(String[] args) {
		FlatArcIJTheme.setup();
        //FlatArcDarkIJTheme.setup();

		/*Menu_principal frame = new Menu_principal();
		frame.setVisible(true);*/
		FichFicharGeneral ventana = new FichFicharGeneral();
		ventana.setVisible(true);
		
	}

}
