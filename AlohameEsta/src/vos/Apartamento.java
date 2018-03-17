package vos;

import org.codehaus.jackson.annotate.*;

public class Apartamento extends Operador {

	////////////////////////////////////////////
	//////////////// ATRIBUTOS///////////////////
	////////////////////////////////////////////

	/**
	 * es o no amoblado el apto
	 */
	@JsonProperty(value = "amoblado")
	private Character amoblado;

	/**
	 * incluye o no servicioPublico
	 */
	@JsonProperty(value = "servicioPublico")
	private Character servicioPublico;

	/**
	 * incluye o no administracion
	 */
	@JsonProperty(value = "administracion")
	private Character administracion;

	/**
	 * tiene o no tv
	 */
	@JsonProperty(value = "tv")
	private Character tv;

	/**
	 * tiene o no internet
	 */
	@JsonProperty(value = "internet")
	private Character internet;

	/**
	 * propietario del apto
	 */
	@JsonProperty(value = "propietario")
	private String propietario;

	/**
	 * precio por mes.
	 */
	@JsonProperty(value = "precio")
	private Double precio;

	////////////////////////////////////////////
	//////////////// CONSTRUCTOR/////////////////
	////////////////////////////////////////////

	/**
	 * 
	 * @param idOperador
	 * @param cupoTotal
	 * @param correo
	 * @param nombre
	 * @param amoblado
	 * @param servicioPublico
	 * @param administracion
	 * @param tv
	 * @param internet
	 * @param propietario
	 * @param precio
	 */
	public Apartamento(@JsonProperty(value = "idOperador") Long idOperador,
			@JsonProperty(value = "cupoTotal") Integer cupoTotal, @JsonProperty(value = "correo") String correo,
			@JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "amoblado") Character amoblado,
			@JsonProperty(value = "servicioPublico") Character servicioPublico,
			@JsonProperty(value = "administracion") Character administracion, @JsonProperty(value = "tv") Character tv,
			@JsonProperty(value = "internet") Character internet,
			@JsonProperty(value = "propietario") String propietario, @JsonProperty(value = "precio") Double precio) {
		super(idOperador, cupoTotal, correo, nombre);
		this.amoblado = amoblado;
		this.servicioPublico = servicioPublico;
		this.administracion = administracion;
		this.tv = tv;
		this.internet = internet;
		this.propietario = propietario;
		this.precio = precio;
	}

	////////////////////////////////////////////
	//////////// GETTERS AND SETTERS/////////////
	////////////////////////////////////////////
	/**
	 * @return the amoblado
	 */
	public Character getAmoblado() {
		return amoblado;
	}

	/**
	 * @param amoblado
	 *            the amoblado to set
	 */
	public void setAmoblado(Character amoblado) {
		this.amoblado = amoblado;
	}

	/**
	 * @return the servicioPublico
	 */
	public Character getServicioPublico() {
		return servicioPublico;
	}

	/**
	 * @param servicioPublico
	 *            the servicioPublico to set
	 */
	public void setServicioPublico(Character servicioPublico) {
		this.servicioPublico = servicioPublico;
	}

	/**
	 * @return the administracion
	 */
	public Character getAdministracion() {
		return administracion;
	}

	/**
	 * @param administracion
	 *            the administracion to set
	 */
	public void setAdministracion(Character administracion) {
		this.administracion = administracion;
	}

	/**
	 * @return the tv
	 */
	public Character getTv() {
		return tv;
	}

	/**
	 * @param tv
	 *            the tv to set
	 */
	public void setTv(Character tv) {
		this.tv = tv;
	}

	/**
	 * @return the internet
	 */
	public Character getInternet() {
		return internet;
	}

	/**
	 * @param internet
	 *            the internet to set
	 */
	public void setInternet(Character internet) {
		this.internet = internet;
	}

	/**
	 * @return the propietario
	 */
	public String getPropietario() {
		return propietario;
	}

	/**
	 * @param propietario
	 *            the propietario to set
	 */
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
