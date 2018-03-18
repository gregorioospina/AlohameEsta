package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.*;

public class DAOReserva {

	/////////////////////////
	//////// CONSTANTES///////
	/////////////////////////

	/**
	 * Constante para indicar el usuario Oracle
	 */
	public final static String USUARIO = "ISIS2304A311810";

	/////////////////////////
	//////// ATRIBUTOS////////
	/////////////////////////

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	///////////////////////////////////
	//// METODOS DE INICIALIZACION/////
	///////////////////////////////////

	/**
	 * Metodo constructor de la clase DAOReserva
	 */
	public DAOReserva() {
		recursos = new ArrayList<Object>();
	}

	////////////////////////////////////////
	///// METODOS DE CONEXION CON LA BD/////
	////////////////////////////////////////
	
	
	

	////////////////////////////////
	////// METODOS AUXILIARES////////
	////////////////////////////////

	public Reserva convertResultToReserva(ResultSet resultSet) throws SQLException {
		Long idReserva = resultSet.getLong("ID_RESERVA");
		Long codigoUsuario = resultSet.getLong("CODIGOUNIANDINO");
		Long idHabitacion = resultSet.getLong("ID_HABITACION");
		Long idOperador = resultSet.getLong("ID_OPERADOR");
		Double precio = resultSet.getDouble("PRECIO");
		String cancelado = resultSet.getString("CANCELADO");
		Date fechaInicial = resultSet.getDate("FECHA_INICIAL");
		Date fechaFinal = resultSet.getDate("FECHA_FINAL");
		
		Reserva reserva = new Reserva(idReserva, codigoUsuario, idOperador, cancelado, precio, idHabitacion, fechaInicial, fechaFinal);

		return reserva;
	}

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a
	 * partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * 
	 * @param connection
	 *            la conexion generada en el TransactionManager para la
	 *            comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection) {
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de
	 * recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido
	 * cerrados.
	 */
	public void cerrarRecursos() {
		for (Object ob : recursos) {
			if (ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

}
