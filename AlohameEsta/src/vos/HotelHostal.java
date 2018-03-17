package vos;

import org.codehaus.jackson.annotate.*;

public class HotelHostal extends Operador {

	////////////////////////////////////////////
	//////////////// ATRIBUTOS///////////////////
	////////////////////////////////////////////

	@JsonProperty(value = "restaurante")
	private Character restaurante;

	@JsonProperty(value = "piscina")
	private Character piscina;

	@JsonProperty(value = "parqueadero")
	private Character parqueadero;

	@JsonProperty(value = "wifi")
	private Character wifi;

	@JsonProperty(value = "tvCable")
	private Character tvCable;

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
	 */
	public HotelHostal(@JsonProperty(value = "idOperador") Long idOperador,
			@JsonProperty(value = "cupoTotal") Integer cupoTotal, @JsonProperty(value = "correo") String correo,
			@JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "restaurante") Character restaurante,
			@JsonProperty(value = "piscina") Character piscina,
			@JsonProperty(value = "parqueadero") Character parqueadero, @JsonProperty(value = "wifi") Character wifi,
			@JsonProperty(value = "tvCable") Character tvCable) {
		super(idOperador, cupoTotal, correo, nombre);
		this.restaurante = restaurante;
		this.piscina = piscina;
		this.parqueadero = parqueadero;
		this.wifi = wifi;
		this.tvCable = tvCable;
	}

	////////////////////////////////////////////
	//////////// GETTERS AND SETTERS/////////////
	////////////////////////////////////////////

	/**
	 * @return the restaurante
	 */
	public Character getRestaurante() {
		return restaurante;
	}

	/**
	 * @param restaurante
	 *            the restaurante to set
	 */
	public void setRestaurante(Character restaurante) {
		this.restaurante = restaurante;
	}

	/**
	 * @return the piscina
	 */
	public Character getPiscina() {
		return piscina;
	}

	/**
	 * @param piscina
	 *            the piscina to set
	 */
	public void setPiscina(Character piscina) {
		this.piscina = piscina;
	}

	/**
	 * @return the parqueadero
	 */
	public Character getParqueadero() {
		return parqueadero;
	}

	/**
	 * @param parqueadero
	 *            the parqueadero to set
	 */
	public void setParqueadero(Character parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * @return the wifi
	 */
	public Character getWifi() {
		return wifi;
	}

	/**
	 * @param wifi
	 *            the wifi to set
	 */
	public void setWifi(Character wifi) {
		this.wifi = wifi;
	}

	/**
	 * @return the tvCable
	 */
	public Character getTvCable() {
		return tvCable;
	}

	/**
	 * @param tvCable
	 *            the tvCable to set
	 */
	public void setTvCable(Character tvCable) {
		this.tvCable = tvCable;
	}

}
