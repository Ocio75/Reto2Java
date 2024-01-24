package com.grupo2.BBDD.conexion;

import java.sql.*;

public class Conexion {
	private static Conexion instancia = null; 
	private final String ip = "127.0.0.1";
	private final int port = 3306;
	private final String BBDD = "reto2somo";
	private final String user = "root";
	private final String passwd = "";
	private final String cadena = "jdbc:mysql://" + ip + ":" + port + "/" + BBDD;
	private final String driver = "com.mysql.jdbc.Driver";
	protected static Connection con;

    private  Conexion(){
        try {
		Class.forName(driver);
		con = DriverManager.getConnection(cadena, user, passwd);
			if (con != null) {
				System.out.println("Conexion a BD " + ip + " con exito");
			} else {
				System.out.println("Imposible conexión con " + ip);
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Excepción de clase no encontrada " + ex);
		} catch (SQLException ex) {
			System.out.println("Excepción de SQL " + ex);
		}
    }
    public static Conexion getInstancia(){
    	if (instancia == null) instancia = new Conexion();
    	return instancia;
    	 } 
    public Connection getCon() {
    	if (con == null) {
            System.err.println("Error: La conexión es nula.");
        }
    	if(con!=null) {
    		return con;
    	}else {
    		return null;
    	}
    	
    }
    public void Cerrar() throws SQLException{
        if (con != null) {
            if (!con.isClosed()) {
                con.close();
            }
        }
    }
}
