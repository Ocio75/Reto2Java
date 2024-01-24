package com.grupo2.Interfaces;

import java.util.ArrayList;

import javax.swing.JTable;

/**
 * Interfaz que define el patrón DAO (Data Access Object) para operaciones CRUD
 * en una tabla de base de datos.
 *
 * @param <TipoGen> Tipo genérico que representa la entidad de la base de datos.
 */
public interface Patron_DAO<TipoGen> {

    /**
     * Inserta un nuevo registro en la base de datos.
     *
     * @param t Objeto que representa el registro a ser insertado.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(TipoGen t);

    /**
     * Elimina un registro de la base de datos identificado por su clave primaria.
     *
     * @param pk Clave primaria del registro a ser eliminado.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean borrar(Object pk);

    /**
     * Actualiza un registro existente en la base de datos.
     *
     * @param t Objeto que representa el registro actualizado.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(TipoGen t);

    /**
     * Busca un registro en la base de datos por su clave primaria.
     *
     * @param pk Clave primaria del registro a ser buscado.
     * @return Objeto que representa el registro encontrado, o null si no se encontró.
     */
    public TipoGen buscar(Object pk);

    /**
     * Devuelve una lista de todos los registros de la tabla en la base de datos.
     *
     * @return ArrayList que contiene todos los registros de la tabla.
     */
    public ArrayList<TipoGen> listarTodos();

    /**
     * Carga los datos de la tabla en un componente JTable.
     *
     * @param t JTable en el que se cargarán los datos.
     */
    public void cargarTabla(JTable t);
}
