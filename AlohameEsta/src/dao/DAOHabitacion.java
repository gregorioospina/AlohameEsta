package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.*;

public class DAOHabitacion {
	/////////////////////////
	//////// CONSTANTES///////
	/////////////////////////

	/**
	 * Constante para indicar el usuario de Oracle.
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

	/////////////////////////////////
	//// METODOS DE INICIALIZACION////
	/////////////////////////////////

	public DAOHabitacion() {
		recursos = new ArrayList<Object>();
	}

	///////////////////////////////////////
	///// METODOS DE CONEXION CON LA BD/////
	///////////////////////////////////////

	/**
	 * Metodo que obtiene la informacion de todas las habitaciones en la Base de
	 * Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @return lista con la informacion de todas las habitaciones que se
	 *         encuentran en la Base de Datos
	 * @throws SQLException
	 *             Genera excepcion si hay error en la conexion o en la consulta
	 *             SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<Habitacion> getHabitaciones() throws SQLException, Exception {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		String sq1 = String.format("SELECT * FROM %1$s.HABITACIONES", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sq1);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;

	}

	/**
	 * Metodo que obtiene la informacion de todas las habitaciones disponibles
	 * en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @return lista con la informacion de todas las habitaciones que se
	 *         encuentran en la Base de Datos
	 * @throws SQLException
	 *             Genera excepcion si hay error en la conexion o en la consulta
	 *             SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<Habitacion> getHabitacionesDisponibles() throws SQLException, Exception {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		String sq1 = String.format("SELECT * FROM %1$s.HABITACIONES WHERE OCUPADO = '0'", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sq1);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;

	}

	/**
	 * Metodo que obtiene la informacion de todas las habitaciones de un
	 * operador que esten disponibles en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @return lista con la informacion de todas las habitaciones que se
	 *         encuentran en la Base de Datos
	 * @throws SQLException
	 *             Genera excepcion si hay error en la conexion o en la consulta
	 *             SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<Habitacion> getHabitacionesDisponiblesOperador(Long opID) throws SQLException, Exception {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		String sq1 = String.format("SELECT * FROM %1$s.HABITACIONES WHERE OCUPADO = '0' AND ID_OPERADR = %2$d", USUARIO,
				opID);

		PreparedStatement prepStmt = conn.prepareStatement(sq1);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;

	}

	/**
	 * Metodo que agregar la informacion de un nuevo habitacion en la Base de
	 * Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param habitacion
	 *            habitacion que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addHabitacion(Habitacion habitacion) throws SQLException, Exception {
		String sq1 = String.format(
				"INSERT INTO %1$s.HABITACION (ID_OPERADOR, ID_HABITACION, TIPO, CUPO, PRECIO, UBICACION, NUMERO, MENAJE, OCUPADO) VALUES (%2$d, %3$d, %4$s, %5$d, %6$f, %7$s, %8$d, %9$c, %10$c)",
				habitacion.getIdOperador(), habitacion.getIdHabitacion(), habitacion.getTipo(), habitacion.getCupo(),
				habitacion.getPrecio(), habitacion.getUbicacion(), habitacion.getNumero(), habitacion.getMenaje(),
				habitacion.getOcupado());

		System.out.println(sq1);

		PreparedStatement prepStmt = conn.prepareStatement(sq1);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza la informacion del usuario en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param habitacion
	 *            usuario que desea actualizar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void updateUsuario(Habitacion habitacion) throws SQLException, Exception {
		StringBuilder sq1 = new StringBuilder();
		sq1.append(String.format("UPDATE %s.HABITACION SET ", USUARIO));
		sq1.append(String.format(
				"ID_OPERADOR  = '%1$d' AND ID_HABITACION = '%2$d' AND TIPO = '%3$s' AND CUPO = '%4$d' AND PRECIO = '%5$f' AND UBICACION = '%6$s' "
						+ "AND NUMERO = '%7$d' AND MENAJE = '%2$c' AND OCUPADO = '%2$c'",
				habitacion.getIdOperador(), habitacion.getIdHabitacion(), habitacion.getTipo(), habitacion.getCupo(),
				habitacion.getPrecio(), habitacion.getUbicacion(), habitacion.getNumero(), habitacion.getMenaje(),
				habitacion.getOcupado()));
		sq1.append(String.format("WHERE ID_HABITACION = '%d'", habitacion.getIdHabitacion()));

		System.out.println(sq1);

		PreparedStatement prepStmt = conn.prepareStatement(sq1.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Metodo que actualiza la informacion del habitacion en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param habitacion habitacion que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteHabitacion(Habitacion habitacion) throws SQLException, Exception
	{
		String sq1 = String.format("DELETE FROM %1$s.HABITACION WHERE ID_HABITACION = %2$d",  USUARIO, habitacion.getIdHabitacion());
		
		System.out.println(sq1);
		
		PreparedStatement prepStmt = conn.prepareStatement(sq1);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	

	////////////////////////////////
	////// METODOS AUXILIARES////////
	////////////////////////////////

	private Habitacion convertResultSetToHabitacion(ResultSet result) throws SQLException, Exception {
		Long idOperador = result.getLong("ID_OPERADOR");
		String tipo = result.getString("TIPO");
		Integer cupo = result.getInt("CUPO");
		Double precio = result.getDouble("PRECIO");
		String ubicacion = result.getString("UBICACION");
		Integer numero = result.getInt("NUMERO");
		Boolean menaje = result.getBoolean("MENAJE");
		Long idHabitacion = result.getLong("ID_HABITACION");
		Boolean ocupado = result.getBoolean("OCUPADO");

		Habitacion ha = new Habitacion(idOperador, idHabitacion, tipo, cupo, precio, ubicacion, numero, menaje,
				ocupado);

		return ha;
	}
	
	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}
	
	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

}
