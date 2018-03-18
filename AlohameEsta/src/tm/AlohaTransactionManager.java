package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.*;
import vos.*;

public class AlohaTransactionManager {

	//////////////////////////////
	////////// CONSTANTES//////////
	//////////////////////////////

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos
	 * de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los
	 * datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;

	private final static Integer CANTIDAD_MAXIMA_HOTELES = 10;

	private final static Integer CANTIDAD_MAXIMA_HOSTELES = 12;

	///////////////////////////////
	////////// ATRIBUTOS////////////
	///////////////////////////////

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de
	 * datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String driver;

	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;

	////////////////////////////////////////
	// METODOS DE CONEXION E INICIALIZACION//
	////////////////////////////////////////

	public AlohaTransactionManager(String contextPathP) {

		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
	 * @throws ClassNotFoundException 
	 */
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(AlohaTransactionManager.CONNECTION_DATA_PATH));
		Properties properties = new Properties();
		
		properties.load(fileInputStream);
		fileInputStream.close();
		
		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");
		
		//Class.forName(driver);
	}
	
	
	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[ALOHA APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	
	///////////////////////////////
	////METODOS TRANSACCIONALES////
	///////////////////////////////
	
	//---------------------------------------------------------------------//
	//-----------------------METODOS USUARIOS------------------------------//
	//---------------------------------------------------------------------//
	
	/**
	 * Metodo que modela la transaccion que retorna todos los usuarioes de la base de datos. <br/>
	 * @return List<Usuario> - Lista de usuarioes que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Usuario> getAllUsuarios() throws Exception {
		DAOUsuario daoUsuario = new DAOUsuario();
		List<Usuario> usuarios;
		try 
		{
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			usuarios = daoUsuario.getUsuario();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el usuario en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del usuario a buscar. id != null
	 * @return Usuario - Usuario que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Usuario getUsuarioByCodigo(Long id) throws Exception {
		DAOUsuario daoUsuario = new DAOUsuario();
		Usuario usuario = null;
		try 
		{
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			usuario = daoUsuario.findUsuarioByCodigo(id);
			if(usuario == null)
			{
				throw new Exception("El usuario con el codigo = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuario;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un usuario a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el usuario que entra como parametro <br/>
	 * @param usuario - el usuario a agregar. usuario != null
	 * @throws Exception - Cualquier error que se genere agregando el usuario
	 */
	public void addUsuario(Usuario usuario) throws Exception 
	{
		
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			daoUsuario.addUsuario(usuario);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al usuario que entra por parametro.<br/>
	 * Solamente se actualiza si existe el usuario en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el usuario que entra como parametro <br/>
	 * @param usuario - Usuario a actualizar. usuario != null
	 * @throws Exception - Cualquier error que se genere actualizando al usuario.
	 */
	public void updateUsuario(Usuario usuario) throws Exception 
	{
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.conn = darConexion();
			daoUsuario.setConn( conn );
			if(daoUsuario.findUsuarioByCodigo(usuario.getCodigoUniandes()) == null)
			{
				throw new Exception("El usuario al que esta intentando modificar no existe");
			}
			daoUsuario.updateUsuario(usuario);		

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al usuario que entra por parametro. <br/>
	 * Solamente se actualiza si existe el usuario en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el usuario que entra por parametro <br/>
	 * @param Usuario - usuario a eliminar. usuario != null
	 * @throws Exception - Cualquier error que se genere eliminando al usuario.
	 */
	public void deleteUsuario(Usuario usuario) throws Exception 
	{
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.conn = darConexion();
			daoUsuario.setConn( conn );
			if(daoUsuario.findUsuarioByCodigo(usuario.getCodigoUniandes()) == null)
			{
				throw new Exception("El usuario que esta intentando eliminar no existe");
			}
			daoUsuario.deleteUsuario(usuario);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	
		//---------------------------------------------------------------------//
		//-----------------------METODOS RESERVA-------------------------------//
		//---------------------------------------------------------------------//
		
		/**
		 * Metodo que modela la transaccion que retorna todos los reserva de la base de datos. <br/>
		 * @return List<Reserva> - Lista de reservaes que contiene el resultado de la consulta.
		 * @throws Exception -  Cualquier error que se genere durante la transaccion
		 */
		public List<Reserva> getAllReservas() throws Exception {
			DAOReserva daoReserva = new DAOReserva();
			List<Reserva> reservas;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				reservas = daoReserva.getReserva();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservas;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el reserva en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del reserva a buscar. id != null
		 * @return Reserva - Reserva que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Reserva getReservaById(Long id) throws Exception {
			DAOReserva daoReserva = new DAOReserva();
			Reserva reserva = null;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				reserva = daoReserva.findReservaById(id);
				if(reserva == null)
				{
					throw new Exception("El reserva con el codigo = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reserva;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el reserva en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del reserva a buscar. id != null
		 * @return Reserva - Reserva que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public ArrayList<Reserva> getReservaByUsuarioOperador(Long codigo, Long opID) throws Exception {
			ArrayList<Reserva> respu = new ArrayList<>();
			DAOReserva daoReserva = new DAOReserva();
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				respu = daoReserva.findReservaByUsuarioOperador(codigo, opID);

			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return respu;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un reserva a la base de datos. <br/>
		 * <b> post: </b> se ha agregado el reserva que entra como parametro <br/>
		 * @param reserva - el reserva a agregar. reserva != null
		 * @throws Exception - Cualquier error que se genere agregando el reserva
		 */
		public void addReserva(Reserva reserva) throws Exception 
		{
			
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				daoReserva.addReserva(reserva);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza en la base de datos al reserva que entra por parametro.<br/>
		 * Solamente se actualiza si existe el reserva en la Base de Datos <br/>
		 * <b> post: </b> se ha actualizado el reserva que entra como parametro <br/>
		 * @param reserva - Reserva a actualizar. reserva != null
		 * @throws Exception - Cualquier error que se genere actualizando al reserva.
		 */
		public void updateReserva(Reserva reserva) throws Exception 
		{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				if(daoReserva.findReservaById(reserva.getIdReserva()) == null)
				{
					throw new Exception("El reserva al que esta intentando modificar no existe");
				}
				daoReserva.updateReservas(reserva);		

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		/**
		 * Metodo que modela la transaccion que elimina de la base de datos al reserva que entra por parametro. <br/>
		 * Solamente se actualiza si existe el reserva en la Base de Datos <br/>
		 * <b> post: </b> se ha eliminado el reserva que entra por parametro <br/>
		 * @param Reserva - reserva a eliminar. reserva != null
		 * @throws Exception - Cualquier error que se genere eliminando al reserva.
		 */
		public void deleteReserva(Reserva reserva) throws Exception 
		{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				if(daoReserva.findReservaById(reserva.getIdReserva()) == null)
				{
					throw new Exception("El reserva que esta intentando eliminar no existe");
				}
				daoReserva.deleteReserva(reserva);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
