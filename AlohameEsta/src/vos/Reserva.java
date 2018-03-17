package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.*;

public class Reserva {
	////////////////////////////////////////////
	//////////////// ATRIBUTOS///////////////////
	////////////////////////////////////////////

	/**
	 * Fecha y hora en la que se efectuo la reserva.
	 */
	@JsonProperty(value = "fechaInicio")
	private Date fechaInicio;

	/**
	 * Cancelado, booleano que especifica si ha sido cancelada la reserva
	 */
	@JsonProperty(value = "cancelado")
	private Character cancelado;

	/**
	 * usuarioID del usuario que efectuo la reserva
	 */
	@JsonProperty(value = "usuarioID")
	private Long usuarioID;

	/**
	 * operadorID del operador ofreciendo el servicio
	 */
	@JsonProperty(value = "operadorID")
	private Long operadorID;

	/**
	 * habitacionID reservado por el usuario
	 */
	@JsonProperty(value = "habitacionID")
	private Long habitacionID;

	/**
	 * Valor a pagar por la reserva
	 */
	@JsonProperty(value = "precio")
	private Double precio;

	/**
	 * Id unico de la reserva
	 */
	@JsonProperty(value = "idReserva")
	private Long idReserva;

	////////////////////////////////////////////
	//////////////// CONSTRUCTOR/////////////////
	////////////////////////////////////////////

	/**
	 * Metodo constructor de la clase Reserva <b> post: </b> Crea la reserva con
	 * los valores por parametro.
	 * 
	 * @param fechaInicio
	 * @param cancelado
	 * @param usuarioID
	 * @param operadorID
	 * @param habitacionID
	 * @param idReserva
	 */
	public Reserva(@JsonProperty(value = "idReserva") Long idReserva,
			@JsonProperty(value = "fechaInicio") Date fechaInicio,
			@JsonProperty(value = "cancelado") Character cancelado, @JsonProperty(value = "usuarioID") Long usuarioID,
			@JsonProperty(value = "operadorID") Long operadorID,
			@JsonProperty(value = "habitacionID") Long habitacionID) {
		this.idReserva = idReserva;
		this.fechaInicio = fechaInicio;
		this.cancelado = cancelado;
		this.usuarioID = usuarioID;
		this.operadorID = operadorID;
		this.habitacionID = habitacionID;
	}

	////////////////////////////////////////////
	//////////// GETTERS AND SETTERS/////////////
	////////////////////////////////////////////

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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

	/**
	 * @return the cancelado
	 */
	public Character getCancelado() {
		return cancelado;
	}

	/**
	 * @param cancelado
	 *            the cancelado to set
	 */
	public void setCancelado(Character cancelado) {
		this.cancelado = cancelado;
	}

	/**
	 * @return the usuarioID
	 */
	public Long getUsuarioID() {
		return usuarioID;
	}

	/**
	 * @param usuarioID
	 *            the usuarioID to set
	 */
	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	/**
	 * @return the operadorID
	 */
	public Long getOperadorID() {
		return operadorID;
	}

	/**
	 * @param operadorID
	 *            the operadorID to set
	 */
	public void setOperadorID(Long operadorID) {
		this.operadorID = operadorID;
	}

	/**
	 * @return the habitacionID
	 */
	public Long getHabitacionID() {
		return habitacionID;
	}

	/**
	 * @param habitacionID
	 *            the habitacionID to set
	 */
	public void setHabitacionID(Long habitacionID) {
		this.habitacionID = habitacionID;
	}

	/**
	 * @return the idReserva
	 */
	public Long getIdReserva() {
		return idReserva;
	}

	/**
	 * @param idReserva
	 *            the idReserva to set
	 */
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

}
