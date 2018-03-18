package vos;

import java.util.Date;
import org.codehaus.jackson.annotate.*;

public class Hostal extends HotelHostal {

	////////////////////////////////////////////
	//////////////// ATRIBUTOS///////////////////
	////////////////////////////////////////////

	/**
	 * Hora de apertura de el hostal
	 */
	@JsonProperty(value = "horaApertura")
	private Date horaApertura;

	/**
	 * Hora de cierre del hotel
	 */
	@JsonProperty(value = "horaCierre")
	private Date horaCierre;

	////////////////////////////////////////////
	//////////////// CONSTRUCTOR/////////////////
	////////////////////////////////////////////

	/**
	 * @param idOperador
	 * @param cupoTotal
	 * @param correo
	 * @param nombre
	 * @param restaurante
	 * @param piscina
	 * @param parqueadero
	 * @param wifi
	 * @param tvCable
	 * @param recepcion247
	 * @param horaApertura
	 * @param horaCierre
	 */
	public Hostal(@JsonProperty(value = "idOperador") Long idOperador,
			@JsonProperty(value = "cupoTotal") Integer cupoTotal, @JsonProperty(value = "correo") String correo,
			@JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "restaurante") Boolean restaurante,
			@JsonProperty(value = "piscina") Boolean piscina,
			@JsonProperty(value = "parqueadero") Boolean parqueadero, @JsonProperty(value = "wifi") Boolean wifi,
			@JsonProperty(value = "tvCable") Boolean tvCable,
			@JsonProperty(value = "recepcion247") Boolean recepcion247,
			@JsonProperty(value = "horaApertura") Date horaApertura,
			@JsonProperty(value = "horaCierre") Date horaCierre) {
		super(idOperador, cupoTotal, correo, nombre, restaurante, piscina, parqueadero, wifi, tvCable);
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}

	////////////////////////////////////////////
	//////////// GETTERS AND SETTERS/////////////
	////////////////////////////////////////////

	/**
	 * @return the horaApertura
	 */
	public Date getHoraApertura() {
		return horaApertura;
	}

	/**
	 * @param horaApertura
	 *            the horaApertura to set
	 */
	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * @return the horaCierre
	 */
	public Date getHoraCierre() {
		return horaCierre;
	}

	/**
	 * @param horaCierre
	 *            the horaCierre to set
	 */
	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

}
