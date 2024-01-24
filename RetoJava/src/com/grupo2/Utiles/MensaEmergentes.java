
package com.grupo2.Utiles;

import javax.swing.JOptionPane;

public class MensaEmergentes {
       public static int alerta(int tipoIcono, String mensaje, String cabecera) {
        int cancelar = 1;
        Object[] options = {"Aceptar"};
        int respuesta = JOptionPane.showOptionDialog(null,mensaje, cabecera,JOptionPane.DEFAULT_OPTION,
                getIcono(tipoIcono),null,options,cancelar);
        return respuesta;
    }
    public static int siNo(int tipo, String mensaje, String cabecera) {
        Object[] options = { "Si", "No" }; // Array con las opciones
        int respuesta = JOptionPane.showOptionDialog(null, mensaje,cabecera,JOptionPane.YES_NO_OPTION, 
                getIcono(tipo),null,options, options[1]);
        return respuesta;
    }
    public static int aceptarCancelar(int tipo, String mensaje, String cabecera) {
        Object[] options = { "Aceptar", "Cancelar" }; // array con las opciones
        int respuesta = JOptionPane.showOptionDialog(null,mensaje,cabecera,JOptionPane.OK_CANCEL_OPTION,
            getIcono(tipo),null,options,options[1]);
        return respuesta;
    }
    public static int siNoCancelar(int tipo, String mensaje, String cabecera) {
        Object[] options={"Si", "No", "Cancelar"};
        int respuesta=JOptionPane.showOptionDialog(null, mensaje, cabecera, JOptionPane.YES_NO_CANCEL_OPTION,
                        getIcono(tipo), null, options, options[2]);
        return respuesta;
    }
    public static int modificarEliminar(int tipo, String mensaje, String cabecera) {
        Object[] options={"Modificar", "Eliminar", "Cancelar"};
        int respuesta=JOptionPane.showOptionDialog(null, mensaje, cabecera, JOptionPane.YES_NO_CANCEL_OPTION,
                        getIcono(tipo), null, options, options[2]);
        return respuesta;
    }
    public static int previsualizarGuardad(int tipo, String mensaje, String cabecera) {
        Object[] options={"Previsualizar", "Guardar", "Cancelar"};
        int respuesta=JOptionPane.showOptionDialog(null, mensaje, cabecera, JOptionPane.YES_NO_CANCEL_OPTION,
                        getIcono(tipo), null, options, options[2]);
        return respuesta;
    }
    
    private static int getIcono(int tipoIcono) {
        switch (tipoIcono) {
            case 1: return JOptionPane.INFORMATION_MESSAGE;
            case 2: return JOptionPane.WARNING_MESSAGE;
            case 3: return JOptionPane.QUESTION_MESSAGE;
            case 4: return JOptionPane.ERROR_MESSAGE;
            case 5:
            default: return JOptionPane.PLAIN_MESSAGE;
        }
    }

}

