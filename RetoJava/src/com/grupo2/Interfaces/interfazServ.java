package com.grupo2.Interfaces;


import java.rmi.*;

public interface interfazServ extends Remote{
		String tiempo(String url) throws RemoteException;

}
