package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

import vos.Apartamento;
import vos.Hostal;
import vos.Hotel;
import vos.HotelHostal;
import vos.Operador;
import vos.PersonaNatural;
import vos.Vivienda;

public class DAOOperador {

	public final static String USUARIO = "ISIS2304A311810";
	
	private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOOperador() {
		recursos = new ArrayList<Object>();
	}
	
	public ArrayList<Operador> getAll() throws SQLException{
		ArrayList<Operador> operadores = new ArrayList<Operador>();
		
		String sql = String.format("SELECT * FROM %1$.OPERADORES", USUARIO);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next()) {
			operadores.add(convertResultSetToOperador(rs));
		}
		
		return operadores;
	}
	
	public ArrayList<Operador> getOperadoresByTypo(String tipo) throws SQLException{
		tipo = tipo.toUpperCase();

		ArrayList<Operador> operadores = new ArrayList<Operador>();
		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE TIPO = '%2$s'", USUARIO, tipo);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next()) {
			operadores.add(convertResultSetToOperador(rs));
		}
		
		return operadores;
	}
	
	public Operador findOperadorById(Long id) throws SQLException, Exception 
	{
		Operador operador = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			operador = convertResultSetToOperador(rs);
		}

		return operador;
	}
	
	public void addOperador(Operador operador) throws SQLException {
		String sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE) VALUES(%2$s, '%3$s', %4$s, '%5$s')",
				USUARIO,
				operador.getIdOperador(),
				operador.getCorreo(),
				operador.getCupoTotal(),
				operador.getNombre()
				);
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteOperador(Operador operador) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, operador.getIdOperador());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	public void updateOperador(Operador operador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OPERADORES SET", USUARIO));
		sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$s AND NOMBRE = '%3$s'", operador.getCorreo(),operador.getCupoTotal(),operador.getNombre()));
		sql.append(String.format("WHERE ID_OPERADOR = %d", operador.getIdOperador()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	private Operador convertResultSetToOperador(ResultSet result) throws SQLException {
		
		Long idOperador = result.getLong("ID_OPERADOR");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		Integer cupoTotal = result.getInt("CUPO");
		String tipo=result.getString("TIPO");
		Operador operamela = new Operador(idOperador, cupoTotal, correo, nombre,tipo);
		return operamela;
	}
	
	private Vivienda convertResultSetToVivienda(ResultSet result, ResultSet resultOperador) throws SQLException {
		Long idOperador = result.getLong("ID_OPERADOR");
		Integer diasAlquilada = result.getInt("DIAS_ALQUILADA");
		Integer numeroHabitaciones = result.getInt("NUMERO_HABITACIONES");
		String ubicacion = result.getString("UBIACION");
		Boolean menaje = result.getBoolean("MENAJE");
		Double precio = result.getDouble("PRECIO");
		String seguro = result.getString("SEGURO");
		Integer cupoTotal = resultOperador.getInt("CUPO");
		String correo = resultOperador.getString("CORREO");
		String nombre = resultOperador.getString("NOMBRE");
		String tipo = resultOperador.getString("TIPO");
		Vivienda viviendamela = new Vivienda(idOperador, cupoTotal, correo, nombre, tipo, numeroHabitaciones, ubicacion, menaje, precio, seguro, diasAlquilada);
		return viviendamela;
	}
	
	private PersonaNatural convertResultSetToPersonaNatural(ResultSet result, ResultSet resultOperador) throws SQLException {
		Boolean banhoCompartido = result.getBoolean("BAÑO_COMPARTIDO");
		Double costoServicios = result.getDouble("COSTO_SERVICIOS");
		Long idOperador = result.getLong("ID_OPERADOR");
		Integer cupoTotal = resultOperador.getInt("CUPO");
		String correo = resultOperador.getString("CORREO");
		String nombre = resultOperador.getString("NOMBRE");
		String tipo = resultOperador.getString("TIPO");
		PersonaNatural personaNatural = new PersonaNatural(idOperador, cupoTotal, correo, nombre, tipo, costoServicios, banhoCompartido);
		return personaNatural;
	}
	
	private HotelHostal convertResultSetToHotelHostal(ResultSet result, ResultSet resultOperador) throws SQLException{
		Integer cupoTotal = resultOperador.getInt("CUPO");
		String correo = resultOperador.getString("CORREO");
		String nombre = resultOperador.getString("NOMBRE");
		String tipo = resultOperador.getString("TIPO");
		Long idOperador = resultOperador.getLong("ID_OPERADOR");
		Boolean restaurante = result.getBoolean("RESTAURANTE");
		Boolean piscina = result.getBoolean("PISCINA");
		Boolean parqueadero = result.getBoolean("PARQUEADERO");
		Boolean wifi = result.getBoolean("WIFI");
		Boolean tvCable = result.getBoolean("TVCABLE");
		Long numRegistro = result.getLong("NUMREGISTROSDT");
		String direccion = result.getString("DIRECCION");
		HotelHostal hotelHostal = new HotelHostal(idOperador, cupoTotal, correo, nombre, tipo, restaurante, piscina, parqueadero, wifi, tvCable, numRegistro, direccion);
		return hotelHostal;
	}
	
	private Hostal convertResultSetToHostal(ResultSet result, ResultSet resultOperador, ResultSet resultHostal) throws SQLException {
		Integer cupoTotal = resultOperador.getInt("CUPO");
		String correo = resultOperador.getString("CORREO");
		String nombre = resultOperador.getString("NOMBRE");
		String tipo = resultOperador.getString("TIPO");
		Long idOperador = resultOperador.getLong("ID_OPERADOR");
		Boolean restaurante = result.getBoolean("RESTAURANTE");
		Boolean piscina = result.getBoolean("PISCINA");
		Boolean parqueadero = result.getBoolean("PARQUEADERO");
		Boolean wifi = result.getBoolean("WIFI");
		Boolean tvCable = result.getBoolean("TVCABLE");
		Long numRegistro = result.getLong("NUMREGISTROSDT");
		String direccion = result.getString("DIRECCION");
		Date horaCierra = resultHostal.getDate("HORACIERRE");
		Date horaApertura = resultHostal.getDate("HORAAPERTURA");
		Hostal hostal = new Hostal(idOperador, cupoTotal, correo, nombre, tipo, restaurante, piscina, parqueadero, wifi, tvCable, horaApertura, horaCierra, numRegistro, direccion);
		return hostal;
	}
	
	private Apartamento convertResultSetToApartamento(ResultSet result, ResultSet resultOperador) throws SQLException {
		Integer cupoTotal = resultOperador.getInt("CUPO");
		String correo = resultOperador.getString("CORREO");
		String nombre = resultOperador.getString("NOMBRE");
		String tipo = resultOperador.getString("TIPO");
		Long idOperador = resultOperador.getLong("ID_OPERADOR");
		Boolean amoblado = result.getBoolean("AMOBLADO");
		Double precio = result.getDouble("PRECIO");
		Boolean servicioPublico = result.getBoolean("SERVICIOPUBLICO");
		Boolean tv = result.getBoolean("TV");
		Boolean internet = result.getBoolean("INTERNET");
		Boolean administracion = result.getBoolean("ADMINISTACION");
		Apartamento apartamento = new Apartamento(idOperador, cupoTotal, correo, nombre, tipo, amoblado, servicioPublico, administracion, tv, internet, precio);
		return apartamento;
	}
	
	private 
	
	
}