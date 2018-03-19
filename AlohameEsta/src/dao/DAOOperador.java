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
import vos.ViviendaUni;

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
	
	public ArrayList<Object> getOperadoresByTypo(String tipo) throws Exception{
		tipo = tipo.toUpperCase();

		ArrayList<Object> operadores = new ArrayList<Object>();
		String sql;
		if(tipo.equals("HOSTAL")) {
			sql = String.format("SELECT * FROM %1$S.OPERADORES OP, %1$S.HOTEL HT, %1$S.%2$S WHERE WHERE OP.ID_OPERADOR=HT.ID_OPERADOR AND HS.ID_OPERADOR=HT.ID_OPERADOR;" , USUARIO, tipo);
		}
		else{
			sql = String.format("SELECT * FROM %1$s.%2$s uno INNER JOIN %1$s.OPERADORES OP ON uno.ID_OPERADOR=OP.ID_OPERADOR AND OP.TIPO='%2$s';", USUARIO, tipo);
		}
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		switch(tipo) {
		case "APARTAMENTO": 
			while(rs.next()) {
				operadores.add(convertResultSetToApartamento(rs));
			}
			break;
		case "HOSTAL":
			while(rs.next()) {
				operadores.add(convertResultSetToHostal(rs));
			}
			break;
		case "PERSONANATURAL":
			while(rs.next()) {
				operadores.add(convertResultSetToPersonaNatural(rs));
			}
			break;
		case "VIVIENDA":
			while(rs.next()) {
				operadores.add(convertResultSetToVivienda(rs));
			}
			break;
		case "VIVIENDAUNI":
			while(rs.next()) {
				operadores.add(convertResultSetToViviendaUni(rs));
			}
			break;
		case "HOTEL":
			while(rs.next()) {
				operadores.add(convertResultSetToHotelHostal(rs));
			}
			break;
		default:
			throw new Exception("tipo no es valido");
		}
		return operadores;
	}
	
	public Object findOperadorById(Long id) throws SQLException, Exception 
	{
		Operador operador = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID_OPERADOR = %2$d;", USUARIO, id); 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();
		if(rs.next()) {
			operador = convertResultSetToOperador(rs);
		}
		String tipo = operador.getTipo().toUpperCase();
		if(tipo.equals("HOSTAL")) {
			sql = String.format("SELECT * FROM %1$S.OPERADORES OP, %1$S.HOTEL HT, %1$S.%2$S WHERE WHERE OP.ID_OPERADOR=HT.ID_OPERADOR AND HS.ID_OPERADOR=HT.ID_OPERADOR HS.ID_OPERADOR = %3$d;" , USUARIO, tipo, id);
		}
		else{
			sql = String.format("SELECT * FROM %1$s.%2$s uno INNER JOIN %1$s.OPERADORES OP ON uno.ID_OPERADOR=OP.ID_OPERADOR AND OP.TIPO='%2$s' AND UNO.ID_OPERADOR = %3$d;", USUARIO, tipo, id);
		}
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		rs = prepStmt.executeQuery();
		if(!rs.next()) {
			throw new Exception("Error");
		}
		switch(tipo) {
		case "APARTAMENTO": 
				operador = convertResultSetToApartamento(rs);
			break;
		case "HOSTAL":
				operador=convertResultSetToHostal(rs);
			break;
		case "PERSONANATURAL":
				operador=convertResultSetToPersonaNatural(rs);
			break;
		case "VIVIENDA":
				operador=convertResultSetToVivienda(rs);
			break;
		case "VIVIENDAUNI":
				operador=convertResultSetToViviendaUni(rs);
			break;
		case "HOTEL":
				operador=convertResultSetToHotelHostal(rs);
			break;
		default:
			throw new Exception("tipo no es valido");
		}
		return operador;
	}
	
	public void addOperador(Operador operador) throws SQLException {
		String sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s','%6$s');",
				USUARIO,
				operador.getIdOperador(),
				operador.getCorreo(),
				operador.getCupoTotal(),
				operador.getNombre(),
				operador.getTipo()
				);
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addOperador(Object operador, String tipo) throws Exception {
		String sql;
		tipo = tipo.toUpperCase();
		switch(tipo) {
		case "APARTAMENTO": 
			Apartamento apartamento = (Apartamento)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					apartamento.getIdOperador(),
					apartamento.getCorreo(),
					apartamento.getCupoTotal(),
					apartamento.getNombre(),
					apartamento.getTipo()
					);
			sql += String.format("INSERT INTO %1$s.APARTAMENTO(ID_OPERADOR,AMOBLADO,PRECIO,SERVICIOPUBLICO,TV,INTERNET,ADMINISTRACION) VALUE(%2$d,'%3$c',%4$f,'%5$c','%6$c','%7$c','%8$c');", 
					USUARIO,
					apartamento.getIdOperador(),
					boolToInt(apartamento.getAmoblado()),
					apartamento.getPrecio(),
					boolToInt(apartamento.getServicioPublico()),
					boolToInt(apartamento.getTv()),
					boolToInt(apartamento.getInternet()),
					boolToInt(apartamento.getAdministracion())
					);
			break;
		case "HOSTAL":
			Hostal hostal = (Hostal)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					hostal.getIdOperador(),
					hostal.getCorreo(),
					hostal.getCupoTotal(),
					hostal.getNombre(),
					hostal.getTipo()
					);
			sql += 	String.format("INSERT INTO %1$s.HOTEL(ID_OPERADOR,RESTAURANTE,PISCINA,PARQUEADERO,WIFI,TVCABLE,NUMREGISTROSDT,DIRECCION,TIPO) VALUES(%2$d,'%3$c','%4$c','%5$c','%6$c','%7$c',%8$d,%9$s,%10$s);", 
					USUARIO,
					hostal.getIdOperador(),
					boolToInt(hostal.getRestaurante()),
					boolToInt(hostal.getPiscina()),
					boolToInt(hostal.getParqueadero()),
					boolToInt(hostal.getWifi()),
					boolToInt(hostal.getTvCable()),
					hostal.getNumRegisto(),
					hostal.getDireccion(),
					hostal.getTipo());
			sql += String.format("INSERT INTO %1$s.HOSTAL(ID_OPERADOR,HORACIERRE,HORAAPERTURA) VALUES($2$d,$3$d,$4$d);", 
					USUARIO,
					hostal.getIdOperador(),
					hostal.getHoraCierre(),
					hostal.getHoraApertura()
					);
			break;
		case "PERSONANATURAL":
			PersonaNatural personaNatural = (PersonaNatural)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					personaNatural.getIdOperador(),
					personaNatural.getCorreo(),
					personaNatural.getCupoTotal(),
					personaNatural.getNombre(),
					personaNatural.getTipo()
					);
			sql+= String.format("INSERT INTO %1$s.PERSONANATURAL(ID_OPERADOR,COSTO_SERVICIOS,BA�O_COMPARTIDO) VALUES(%2$d,%3$f,'%4$c');", 
					USUARIO,
					personaNatural.getIdOperador(),
					personaNatural.getCostoServicios(),
					boolToInt(personaNatural.getBanhoCompartido())); 
			break;
		case "VIVIENDA":
			Vivienda vivienda = (Vivienda)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					vivienda.getIdOperador(),
					vivienda.getCorreo(),
					vivienda.getCupoTotal(),
					vivienda.getNombre(),
					vivienda.getTipo()
					);
			sql += String.format("INSERT INTO %1$s.VIVIENDA(ID_OPERADOR,MENAJE,DIAS_ALQUILADA,NUMERO_HABITACIONES,UBICACION,PRECIO,SEGURO) VALUES (%2$d,'%3$c',%4$d,%5$d,'%6$d',%7$f,'%8$d')",
					USUARIO,
					vivienda.getIdOperador(),
					boolToInt(vivienda.getMenaje()),
					vivienda.getDiasAlquilada(),
					vivienda.getNumeroDeHabitaciones(),
					vivienda.getUbicacion(),
					vivienda.getCosto(),
					vivienda.getSeguro());
			break;
		case "VIVIENDAUNI":
			ViviendaUni viviendaUni = (ViviendaUni)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					viviendaUni.getIdOperador(),
					viviendaUni.getCorreo(),
					viviendaUni.getCupoTotal(),
					viviendaUni.getNombre(),
					viviendaUni.getTipo()
					);
			sql+= String.format("INSERT INTO %1%s.VIVIENDAUNI(ID_OPERADOR,SALAS_DE_ESTUDIO_COSTO,RESTAURANTE_COSTO,GIMNASIO_COSTO,UBICACION,CAPACIDAD) VALUES(%2$d, %3$f, %4$f, %5$f, '%6$s',%7$d)",
					USUARIO,
					viviendaUni.getIdOperador(),
					viviendaUni.getSalasDeEstudioCosto(),
					viviendaUni.getRestauranteCosto(),
					viviendaUni.getGimnasioCosto(),
					viviendaUni.getUbicacion(),
					viviendaUni.getCapacidad());
			break;
		case "HOTEL":
			HotelHostal hotel = (HotelHostal)operador;
			sql = String.format("INSERT INTO %1$s.OPERADORES(ID_OPERADOR,CORREO,CUPO,NOMBRE,TIPO) VALUES(%2$d, '%3$s', %4$d, '%5$s',%6$s');",
					USUARIO,
					hotel.getIdOperador(),
					hotel.getCorreo(),
					hotel.getCupoTotal(),
					hotel.getNombre(),
					hotel.getTipo()
					);
			sql += 	String.format("INSERT INTO %1$s.HOTEL(ID_OPERADOR,RESTAURANTE,PISCINA,PARQUEADERO,WIFI,TVCABLE,NUMREGISTROSDT,DIRECCION,TIPO) VALUES(%2$d,'%3$c','%4$c','%5$c','%6$c','%7$c',%8$d,%9$s,%10$s);", 
					USUARIO,
					hotel.getIdOperador(),
					boolToInt(hotel.getRestaurante()),
					boolToInt(hotel.getPiscina()),
					boolToInt(hotel.getParqueadero()),
					boolToInt(hotel.getWifi()),
					boolToInt(hotel.getTvCable()),
					hotel.getNumRegisto(),
					hotel.getDireccion(),
					hotel.getTipo());
			break;
		default:
			throw new Exception("tipo no es valido");
		}
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
		sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
		sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s'", operador.getCorreo(),operador.getCupoTotal(),operador.getNombre(), operador.getTipo()));
		sql.append(String.format(" WHERE ID_OPERADOR = %d;", operador.getIdOperador()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void updateOperador(Object operador, String tipo)throws SQLException, Exception{
	tipo = tipo.toUpperCase();
	StringBuilder sql = new StringBuilder();
	 switch(tipo) {
		case "APARTAMENTO": 
			Apartamento apartamento = (Apartamento)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s' ", apartamento.getCorreo(),apartamento.getCupoTotal(),apartamento.getNombre(), apartamento.getTipo()));
			sql.append(String.format("WHERE ID_OPERADOR = %d;", apartamento.getIdOperador()));
			sql.append(String.format("UPDATE %s.APARTAMENTO SET ", USUARIO));
			sql.append(String.format("AMOBLADO = '%1$c' AND PRECIO = %2$f AND SERVICIOPUBLICO ='%3$c' AND TV = '%4$c' AND INTERNET = '%5$c' AND ADMINISTRACION ='%6$c' ",
					apartamento.getAmoblado(),
					apartamento.getPrecio(), 
					apartamento.getServicioPublico(),
					apartamento.getTv(),
					apartamento.getInternet(),
					apartamento.getAdministracion()));
			sql.append(String.format("WHERE ID_OPERADOR = %d;", apartamento.getIdOperador()));
			break;
		case "HOSTAL":
			Hostal hostal = (Hostal)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s' ", hostal.getCorreo(),hostal.getCupoTotal(),hostal.getNombre(), hostal.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", hostal.getIdOperador()));
			sql.append(String.format("UPDATE %s.HOTEL SET ", USUARIO));
			sql.append(String.format("RESTAURANTE = '%1$c' AND PISCINA ='%2$c' AND PARQUEADERO='%3$c' AND WIFI ='%4$c' AND TVCABLE='%5$c' AND NUMREGISTROSDT =%6$d AND DIRECCION = '%7$s' AND TIPO = '%8$s'", 
					boolToInt(hostal.getRestaurante()),
					boolToInt(hostal.getPiscina()),
					boolToInt(hostal.getParqueadero()),
					boolToInt(hostal.getWifi()),
					boolToInt(hostal.getTvCable()),
					hostal.getNumRegisto(),
					hostal.getDireccion(),
					hostal.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", hostal.getIdOperador()));
			sql.append(String.format("UPDATE %s.HOSTAL SET ", USUARIO));
			sql.append(String.format("HORACIERRE=%1$d AND HORAAPERTURA = %2$d",
					hostal.getHoraCierre(),
					hostal.getHoraApertura()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", hostal.getIdOperador()));
			break;
		case "PERSONANATURAL":
			PersonaNatural personaNatural = (PersonaNatural)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s'", personaNatural.getCorreo(),personaNatural.getCupoTotal(),personaNatural.getNombre(), personaNatural.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", personaNatural.getIdOperador()));
			sql.append(String.format("UPDATE %s.PERSONANATURAL SET ", USUARIO));
			sql.append(String.format("COSTO_SERVICIOS = %1$f AND BA�O_COMPARTIDO= '%2$c'", 
					personaNatural.getCostoServicios(),
					boolToInt(personaNatural.getBanhoCompartido())));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", personaNatural.getIdOperador()));
			break;
		case "VIVIENDA":
			Vivienda vivienda = (Vivienda)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s'", vivienda.getCorreo(),vivienda.getCupoTotal(),vivienda.getNombre(), vivienda.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", vivienda.getIdOperador()));
			sql.append(String.format("UPDATE %s.PERSONANATURAL SET ", USUARIO));
			sql.append(String.format("MENAJE = '%1$c'AND DIAS_ALQUILADA=%2$d AND NUMERO_HABITACIONES=%3$d AND UBICACION = '%4$s' AND PRECIO = %5$f AND SEGURO = '%6$s'", 
					boolToInt(vivienda.getMenaje()),
					vivienda.getDiasAlquilada(),
					vivienda.getNumeroDeHabitaciones(),
					vivienda.getUbicacion(),
					vivienda.getCosto(),
					vivienda.getSeguro()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", vivienda.getIdOperador()));
			break;
		case "VIVIENDAUNI":
			ViviendaUni viviendaUni = (ViviendaUni)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s'", viviendaUni.getCorreo(),viviendaUni.getCupoTotal(),viviendaUni.getNombre(), viviendaUni.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", viviendaUni.getIdOperador()));
			sql.append(String.format("UPDATE %s.VIVIENDAUNI SET ", USUARIO));
			sql.append(String.format("SALAS_DE_ESTUDIO_COSTO = %1$f AND RESTAURANTE_COSTO = %2$f AND GIMNASIO_COSTO = %3$f AND UBICACION='%4$s' AND CAPACIDAD = %5$d", 
					viviendaUni.getSalasDeEstudioCosto(),
					viviendaUni.getRestauranteCosto(),
					viviendaUni.getGimnasioCosto(),
					viviendaUni.getUbicacion(),
					viviendaUni.getCapacidad()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", viviendaUni.getIdOperador()));
			break;
		case "HOTEL":
			HotelHostal hotel = (HotelHostal)operador;
			sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
			sql.append(String.format("CORREO = '%1$s' AND CUPO = %2$d AND NOMBRE = '%3$s' AND TIPO = '%4$s'", hotel.getCorreo(),hotel.getCupoTotal(),hotel.getNombre(), hotel.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", hotel.getIdOperador()));
			sql.append(String.format("UPDATE %s.HOTEL SET ", USUARIO));
			sql.append(String.format("RESTAURANTE = '%1$c' AND PISCINA ='%2$c' AND PARQUEADERO='%3$c' AND WIFI ='%4$c' AND TVCABLE='%5$c' AND NUMREGISTROSDT =%6$d AND DIRECCION = '%7$s' AND TIPO = '%8$s'", 
					boolToInt(hotel.getRestaurante()),
					boolToInt(hotel.getPiscina()),
					boolToInt(hotel.getParqueadero()),
					boolToInt(hotel.getWifi()),
					boolToInt(hotel.getTvCable()),
					hotel.getNumRegisto(),
					hotel.getDireccion(),
					hotel.getTipo()));
			sql.append(String.format(" WHERE ID_OPERADOR = %d;", hotel.getIdOperador()));
			break;
		default:
			throw new Exception("tipo no es valido");
		}
	 System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	 
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////CONVERT_RESULT_SET_TO METHODS////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Operador convertResultSetToOperador(ResultSet result) throws SQLException {
		
		Long idOperador = result.getLong("ID_OPERADOR");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		Integer cupoTotal = result.getInt("CUPO");
		String tipo=result.getString("TIPO");
		Operador operamela = new Operador(idOperador, cupoTotal, correo, nombre,tipo);
		return operamela;
	}
	
	private Vivienda convertResultSetToVivienda(ResultSet result) throws SQLException {
		Long idOperador = result.getLong("ID_OPERADOR");
		Integer diasAlquilada = result.getInt("DIAS_ALQUILADA");
		Integer numeroHabitaciones = result.getInt("NUMERO_HABITACIONES");
		String ubicacion = result.getString("UBIACION");
		Boolean menaje = result.getBoolean("MENAJE");
		Double precio = result.getDouble("PRECIO");
		String seguro = result.getString("SEGURO");
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		Vivienda viviendamela = new Vivienda(idOperador, cupoTotal, correo, nombre, tipo, numeroHabitaciones, ubicacion, menaje, precio, seguro, diasAlquilada);
		return viviendamela;
	}
	
	private PersonaNatural convertResultSetToPersonaNatural(ResultSet result) throws SQLException {
		Boolean banhoCompartido = result.getBoolean("BA�O_COMPARTIDO");
		Double costoServicios = result.getDouble("COSTO_SERVICIOS");
		Long idOperador = result.getLong("ID_OPERADOR");
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		PersonaNatural personaNatural = new PersonaNatural(idOperador, cupoTotal, correo, nombre, tipo, costoServicios, banhoCompartido);
		return personaNatural;
	}
	
	private HotelHostal convertResultSetToHotelHostal(ResultSet result) throws SQLException{
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		Long idOperador = result.getLong("ID_OPERADOR");
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
	
	private Hostal convertResultSetToHostal(ResultSet result) throws SQLException {
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		Long idOperador = result.getLong("ID_OPERADOR");
		Boolean restaurante = result.getBoolean("RESTAURANTE");
		Boolean piscina = result.getBoolean("PISCINA");
		Boolean parqueadero = result.getBoolean("PARQUEADERO");
		Boolean wifi = result.getBoolean("WIFI");
		Boolean tvCable = result.getBoolean("TVCABLE");
		Long numRegistro = result.getLong("NUMREGISTROSDT");
		String direccion = result.getString("DIRECCION");
		Integer horaCierra = result.getInt("HORACIERRE");
		Integer horaApertura = result.getInt("HORAAPERTURA");
		Hostal hostal = new Hostal(idOperador, cupoTotal, correo, nombre, tipo, restaurante, piscina, parqueadero, wifi, tvCable, horaApertura, horaCierra, numRegistro, direccion);
		return hostal;
	}
	
	private Apartamento convertResultSetToApartamento(ResultSet result) throws SQLException {
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		Long idOperador = result.getLong("ID_OPERADOR");
		Boolean amoblado = result.getBoolean("AMOBLADO");
		Double precio = result.getDouble("PRECIO");
		Boolean servicioPublico = result.getBoolean("SERVICIOPUBLICO");
		Boolean tv = result.getBoolean("TV");
		Boolean internet = result.getBoolean("INTERNET");
		Boolean administracion = result.getBoolean("ADMINISTACION");
		Apartamento apartamento = new Apartamento(idOperador, cupoTotal, correo, nombre, tipo, amoblado, servicioPublico, administracion, tv, internet, precio);
		return apartamento;
	}
	
	private ViviendaUni convertResultSetToViviendaUni(ResultSet result) throws SQLException {
		Integer cupoTotal = result.getInt("CUPO");
		String correo = result.getString("CORREO");
		String nombre = result.getString("NOMBRE");
		String tipo = result.getString("TIPO");
		Long idOperador = result.getLong("ID_OPERADOR");
		Double salaDeEstudioCosto = result.getDouble("SALAS_DE_ESTUDIO_COSTO");
		Double restauranteCosto = result.getDouble("RESTAURANTE_COSTO");
		Double gimnasioCosto= result.getDouble("RESTAURANTE_COSTO");
		String ubicacion = result.getString("UBICACION");
		Short capacidad = result.getShort("CAPACIDAD");
		ViviendaUni viendeamela = new ViviendaUni(idOperador, cupoTotal, correo, nombre, tipo, salaDeEstudioCosto, restauranteCosto, gimnasioCosto, ubicacion, capacidad);
		return viendeamela;
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////OTROS/////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private char boolToInt(Boolean bol) {
		return bol?'1':'0';
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