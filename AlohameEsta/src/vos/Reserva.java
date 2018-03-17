package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.*;

public class Reserva 
{
////////////////////////////////////////////
////////////////ATRIBUTOS///////////////////
////////////////////////////////////////////

	/**
	 *  Fecha y hora en la que se efectuo 
	 *  la reserva.
	 */
	@JsonProperty(value = "fechaInicio")
	private Date fechaInicio;
	
	/**
	 * Cancelado, booleano que especifica si ha sido 
	 * cancelada la reserva
	 */
	@JsonProperty(value = "cancelado")
	private Boolean cancelado;
	
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

////////////////////////////////////////////
////////////////CONSTRUCTOR/////////////////
////////////////////////////////////////////

	/**
	 * Metodo constructor de la clase Reserva
	 * <b> post: </b> Crea la reserva con los valores por parametro.
	 * @param fechaInicio
	 * @param cancelado
	 * @param usuarioID
	 * @param operadorID
	 * @param habitacionID
	 */
	public Reserva(@JsonProperty(value = "fechaInicio") Date fechaInicio, @JsonProperty(value = "cancelado") Boolean cancelado, @JsonProperty(value = "usuarioID")Long usuarioID, @JsonProperty(value = "operadorID") Long operadorID, @JsonProperty(value = "habitacionID") Long habitacionID)
	{
		this.fechaInicio = fechaInicio;
		this.cancelado = cancelado;
		this.usuarioID = usuarioID;
		this.operadorID = operadorID;
		this.habitacionID = habitacionID;
	}
	
	
////////////////////////////////////////////
////////////GETTERS AND SETTERS/////////////
////////////////////////////////////////////
	
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}


	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	/**
	 * @return the cancelado
	 */
	public Boolean getCancelado() {
		return cancelado;
	}


	/**
	 * @param cancelado the cancelado to set
	 */
	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}


	/**
	 * @return the usuarioID
	 */
	public Long getUsuarioID() {
		return usuarioID;
	}


	/**
	 * @param usuarioID the usuarioID to set
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
	 * @param operadorID the operadorID to set
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
	 * @param habitacionID the habitacionID to set
	 */
	public void setHabitacionID(Long habitacionID) {
		this.habitacionID = habitacionID;
	}
	
}
